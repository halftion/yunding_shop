import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Lucene与数据库结合使用
 */
public class LuceneDemo01 {

    private static final String driverClassName="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String username="root";
    private static final String password="Yubing.0616";


    private static final Version version = Version.LUCENE_7_4_0;
    private Directory directory = new RAMDirectory();
    private DirectoryReader ireader = null;
    private IndexWriter iwriter = null;
    private IKAnalyzer analyzer;
    //存放索引文件的位置，即索引库
    private String indexDir = "E:\\lucene\\index";
    private static File indexFile = null;
    private Connection conn;
    /**
     * 读取索引文件
     * @return
     * @throws Exception
     */
    public IndexSearcher getSearcher() throws Exception{
        indexFile = new File(indexDir);
        ireader = DirectoryReader.open(FSDirectory.open(indexFile.toPath()));
        return new IndexSearcher(ireader);
    }
    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection(){
        if(this.conn == null){
            try {
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    /**
     * 中文分词工具
     * @return
     */
    private IKAnalyzer getAnalyzer(){
        if(analyzer == null){
            return new IKAnalyzer();
        }else{
            return analyzer;
        }
    }
    /**
     * 创建索引文件
     */
    public void createIndex(){
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        if(conn == null){
            System.out.println("get the connection error...");
            return ;
        }
        String sql = "select id,name from `t_luncene`";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            /**
             * 获取索引文件位置
             */
            indexFile = new File(indexDir);
            if(!indexFile.exists()) {
                indexFile.mkdirs();     
            }  
            /**
             * 设置索引参数
             */
            directory = FSDirectory.open(indexFile.toPath());
            IndexWriterConfig iwConfig =  new IndexWriterConfig(getAnalyzer());
            iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            iwriter = new IndexWriter(directory,iwConfig);
            /*lucene本身不支持更新 
             *  
             * 通过删除索引然后再建立索引来更新 
             */ 
            iwriter.deleteAll();//删除上次的索引文件，重新生成索引
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Document doc = new Document();
                doc.add(new TextField("id", id+"", Field.Store.YES));
                doc.add(new TextField("name", name+"",Field.Store.YES));
//                iwriter.addDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(iwriter != null)
                iwriter.close();
/*                rs.close();
                pstmt.close();*/
                if(!conn.isClosed()){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
    /**
     * 查询方法
     * @param field 字段名称
     * @param keyword 字段值
     * @param num 数量
     */
    public void searchByTerm(String field,String keyword,int num) throws Exception{
         IndexSearcher isearcher = getSearcher();
         Analyzer analyzer =  getAnalyzer();
        //使用QueryParser查询分析器构造Query对象
        QueryParser qp = new QueryParser(field,analyzer);
      
        qp.setDefaultOperator(QueryParser.OR_OPERATOR);
        try {
            Query query = qp.parse(keyword);
            ScoreDoc[] hits;


            //注意searcher的几个方法
            hits = isearcher.search(query, num).scoreDocs;


            System.out.println("the ids is =");
            for (ScoreDoc hit : hits) {
                Document doc = isearcher.doc(hit.doc);
                System.out.print(doc.get("id") + " ");
                System.out.println(doc.get("name") + " ");
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    /**   
     * 搜索索引   
     */    
    public static void main(String[] args) throws Exception {
        LuceneDemo01 ld = new LuceneDemo01();
//        ld.createIndex();
        ld.searchByTerm("name", "玉", 100);

    }
}