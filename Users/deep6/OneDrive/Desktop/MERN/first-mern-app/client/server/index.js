const express = require("express");
const cors = require("cors");
const mongoose = require("mongoose");
const Student = require("./models/Student");
const app = express();
const studentRoutes = require("./routes/studentRoutes");

// MongoDB Connection
mongoose.connect(
  "mongodb://ishika27parmar_db_user:ishika27@ac-irqnn9p-shard-00-00.xfrzo53.mongodb.net:27017,ac-irqnn9p-shard-00-01.xfrzo53.mongodb.net:27017,ac-irqnn9p-shard-00-02.xfrzo53.mongodb.net:27017/?ssl=true&replicaSet=atlas-tmaxnm-shard-0&authSource=admin&appName=Cluster0"
)
.then(() => {
  console.log("MongoDB Connected ✅");
})
.catch((error) => {
  console.log("MongoDB Error ❌");
  console.log(error);
});

app.use(cors());
app.use(express.json());
app.use("/api", studentRoutes);
// Home Route
app.get("/", (req, res) => {
  res.send("Hello, Ishika, Backend is working 🚀");
});

// Student Route
/*app.get("/student", (req, res) => {
  res.json({
    name: "Ishika",
    age: 20,
    course: "MERN"
  });
});

// POST Route
app.post("/student", (req, res) => {
  console.log(req.body);

  res.json({
    message: "Student received successfully!"
  });
});*/

// Start Server
app.listen(5000, () => {
  console.log("Server Started on Port 5000");
});