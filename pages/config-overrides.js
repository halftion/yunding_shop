const { injectBabelPlugin } = require('react-app-rewired');
const rewireLess = require('react-app-rewire-less');

module.exports = function override(config, env) {
  config = injectBabelPlugin(['import', { libraryName: 'antd', style: true }], config);
  config = rewireLess.withLoaderOptions({
  javascriptEnabled: true,
  modifyVars: { "@primary-color": "#e31436" },
  })(config, env);
  return config;
};