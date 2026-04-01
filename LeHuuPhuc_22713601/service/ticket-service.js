const { PutObjectCommand, DeleteObjectCommand } = require("@aws-sdk/client-s3");
const {
  ScanCommand,
  GetCommand,
  PutCommand,
  DeleteCommand,
} = require("@aws-sdk/lib-dynamodb");
const { dynamodb, s3 } = require("../config/aws");
const TableName = "";
const Bucket = "";
// getAll
const getAll = async () => {
  const data = await dynamodb.send(new ScanCommand({ TableName }));
  return data.Items;
};
//search
const search = async (key) => {
  const data = await dynamodb.send(new ScanCommand({ TableName }));
  if (!key) return data.Items;
  const lower = key.toLowerCase();
  return data.Items.filter(
    (item) =>
      item.eventName?.toLowerCase().includes(lower) ||
      item.holderName?.toLowerCase().includes(lower),
  );
};
// getid
const getByID = async (ticketId) => {
  const data = await dynamodb.send(
    new GetCommand({ TableName, Key: { ticketId } }),
  );
  return data.Item;
};
// create and update
const upsert = async (ticketId, body, file) => {
  const {
    eventName,
    holderName,
    category,
    quantity,
    pricePerTicket,
    eventDate,
    createdAt,
  } = body;
  const { totalAmount, finalAmount, discountLabel } = calculateAmounts(
    quantity,
    pricePerTicket,
    category,
  );
  const status = getTicketStatus(quantity);
  let ticketData = {
    ticketId: ticketId ?? Date.now().toString(),
    eventName,
    holderName,
    category,
    quantity,
    pricePerTicket,
    eventDate,
    totalAmount,
    finalAmount,
    discountLabel,
    status,
    createdAt,
  };
  if (ticketId) {
    const data = await dynamodb.send(
      new GetCommand({ TableName, Key: { ticketId } }),
    );
    if (data.Item)
      ticketData = {
        ...data.Item,
        eventName,
        holderName,
        category,
        quantity,
        pricePerTicket,
        eventDate,
        totalAmount,
        finalAmount,
        discountLabel,
        status,
        createdAt,
      };
  }
  if (file) {
    const key = `${Date.now()}-${file.originalname}`;
    await s3.send(
      new PutObjectCommand({
        Bucket,
        Key: key,
        Body: file.buffer,
        ContentType: file.minetype,
      }),
    );
    const imageUrl = `https://${Bucket}.s3.amazonaws.com/${key}`;
    if (ticketData.imageUrl) {
      await s3.send(
        new DeleteObjectCommand({
          Bucket,
          Key: ticketData.imageUrl.split("/").pop(),
        }),
      );
    }
    ticketData.imageUrl = imageUrl;
  }
  await dynamodb.send(
    new PutCommand({
      TableName,
      Item: ticketData,
    }),
  );
};
const deleteById = async (ticketId) => {
  const data = await dynamodb.send(
    new GetCommand({ TableName, Key: { ticketId } }),
  );
  if (data.Item.imageUrl) {
    await s3.send(
      new DeleteObjectCommand({
        Bucket,
        Key: data.Item.imageUrl.split("/").pop(),
      }),
    );
  }
  await dynamodb.send(new DeleteCommand({ TableName, Key: { ticketId } }));
};
module.exports = { getAll, search, getByID, upsert, deleteById };
// Tính tổng tiền
function calculateAmounts(quantity, pricePerTicket, category) {
  const totalAmount = quantity * pricePerTicket;
  let finalAmount = totalAmount;
  let discountLabel = "Không giảm giá";

  if (category === "VIP" && quantity >= 4) {
    finalAmount = totalAmount * 0.9; // giảm 10%
    discountLabel = "Được giảm giá";
  } else if (category === "VVIP" && quantity >= 2) {
    finalAmount = totalAmount * 0.85; // giảm 15%
    discountLabel = "Được giảm giá";
  }

  return { totalAmount, finalAmount, discountLabel };
}

// Xác định tình trạng vé
function getTicketStatus(quantity) {
  if (quantity <= 0) return "Hết vé";
  if (quantity <= 10) return "Sắp hết";
  return "Còn vé";
}
