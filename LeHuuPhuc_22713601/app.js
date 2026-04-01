const e = require("express");
const app = e();

app.set("view engine", "ejs");
app.set("views", "./views");

const ticketRouter = require("./routes/ticket-router");

app.use("/", ticketRouter);
app.listen(3000, () => {
  console.log("http://localhost:3000");
});
