const fs = require("fs");
module.exports = {
  configureWebpack: {
    // other webpack options to merge in ...
  },

  devServer: {
    // https 적용을 위한 코드
    https: true,
    https: {
      key: fs.readFileSync(
          "/etc/letsencrypt/live/i5a508.p.ssafy.io/privkey.pem"
      ),
      cert: fs.readFileSync(
          "/etc/letsencrypt/live/i5a508.p.ssafy.io/cert.pem"
      ),
      ca: fs.readFileSync(
          "/etc/letsencrypt/live/i5a508.p.ssafy.io/chain.pem"
      ),
    },
    // devServer Options don't belong into `configureWebpack`
    // dev 빌드상태에도 도메인으로 접속하기 위해서 추가한 코드
    host: "0.0.0.0",
    hot: true,
    disableHostCheck: true,
  },
};
