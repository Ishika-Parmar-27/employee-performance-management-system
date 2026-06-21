const express = require("express");
const router = express.Router();
const Student = require("../models/Student");

// CREATE
router.post("/students", async (req, res) => {
  try {
    const student = new Student(req.body);
    await student.save();

    res.status(201).json({
      message: "Student saved successfully",
      student,
    });
  } catch (error) {
    res.status(500).json({
      message: error.message,
    });
  }
});

// READ
router.get("/students", async (req, res) => {
  try {
    const students = await Student.find();
    res.status(200).json(students);
  } catch (error) {
    res.status(500).json({
      message: error.message,
    });
  }
});

// UPDATE
router.put("/students/:id", async (req, res) => {
  try {
    const updatedStudent = await Student.findByIdAndUpdate(
      req.params.id,
      req.body,
      { new: true }
    );

    res.status(200).json(updatedStudent);
  } catch (error) {
    res.status(500).json({
      message: error.message,
    });
  }
});

// DELETE
router.delete("/students/:id", async (req, res) => {
  try {
    const deletedStudent = await Student.findByIdAndDelete(req.params.id);

    res.status(200).json({
      message: "Student deleted successfully",
      deletedStudent,
    });
  } catch (error) {
    res.status(500).json({
      message: error.message,
    });
  }
});

module.exports = router;