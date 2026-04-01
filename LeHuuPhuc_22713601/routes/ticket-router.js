const express = require("express");
const router = express.Router();
const ticketController = require("../controllers/ticket-controller");
const multer = require("multer");

const upload = multer({ storage: multer.memoryStorage() });
router.get("/", ticketController.renderItem);
router.get("/form", ticketController.renderForm);
router.get("/form/:ticketId", ticketController.renderForm);
router.get("/detail/:ticketId", ticketController.renderDetail);

router.post(
  "/tickets",
  upload.single("imageUrl"),
  ticketController.handleUpsert,
);
router.post(
  "/tickets/:ticketId",
  upload.single("imageUrl"),
  ticketController.handleUpsert,
);
router.post("/tickets/delete/:ticketId", ticketController.handleDelete);

module.exports = router;
