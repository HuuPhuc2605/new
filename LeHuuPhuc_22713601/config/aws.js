const { DynamoDBClient } = require("@aws-sdk/client-dynamodb");
const { S3Client } = require("@aws-sdk/client-s3");
const { DynamoDBDocument } = require("@aws-sdk/lib-dynamodb");

const info = {
  region: "",
  credentials: {
    accessKeyId: "",
    secretAccessKey: "",
  },
};
const s3 = new S3Client(info);
const dynamodb = DynamoDBDocument.from(new DynamoDBClient(info));
module.exports = { s3, dynamodb };
