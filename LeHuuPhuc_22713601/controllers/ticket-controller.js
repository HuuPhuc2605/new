const {
  getAll,
  search,
  getByID,
  upsert,
  deleteById,
} = require("../service/ticket-service");
const renderItem = async (req, res) => {
  const w = req.query.w;
  const status = req.query.status;
  let data = w ? await search(w) : await getAll();
  if (status) {
    data = data.filter((t) => t.status === status);
  }
  res.render("index", { tickets: data, success: req.query.success, w, status });
};
const renderForm = async (req, res) => {
  const { ticketId } = req.params;
  const ticket = ticketId ? await getByID(ticketId) : null;
  res.render("form", { ticket });
};
const renderDetail = async (req, res) => {
  const { ticketId } = req.params;
  const ticket = ticketId ? await getByID(ticketId) : null;
  res.render("detail", { ticket });
};
const handleUpsert = async (req, res) => {
  try {
    await upsert(req.params.ticketId, req.body, req.file);
    // không thêm dấu hỏi sẽ không về trang chủ
    res.redirect("/?success=1");
  } catch (error) {
    res.status(500).send(error.message);
  }
};
const handleDelete = async (req, res) => {
  try {
    await deleteById(req.params.ticketId);
    res.redirect("/");
  } catch (error) {
    res.status(500).send(error.message);
  }
};
module.exports = {
  renderItem,
  renderForm,
  renderDetail,
  handleDelete,
  handleUpsert,
};
