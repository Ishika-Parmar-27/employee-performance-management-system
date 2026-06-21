import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [students, setStudents] = useState([]);
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [course, setCourse] = useState("");
  const [editId, setEditId] = useState(null);
  const fetchStudents = async () => {
    try {
      const res = await fetch("http://localhost:5000/api/students");
      const data = await res.json();
      setStudents(data);
    } catch (error) {
      console.log(error);
    }
  };

 const addStudent = async () => {
  try {
    if (editId) {
      await fetch(`http://localhost:5000/api/students/${editId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name,
          age,
          course,
        }),
      });

      setEditId(null);
    } else {
      await fetch("http://localhost:5000/api/students", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name,
          age,
          course,
        }),
      });
    }

    setName("");
    setAge("");
    setCourse("");

    fetchStudents();
  } catch (error) {
    console.log(error);
  }
};
  const editStudent = (student) => {
  setName(student.name);
  setAge(student.age);
  setCourse(student.course);
  setEditId(student._id);
};
  const deleteStudent = async (id) => {
    try {
      await fetch(`http://localhost:5000/api/students/${id}`, {
        method: "DELETE",
      });

      fetchStudents();
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  return (
    <div
      style={{
        maxWidth: "800px",
        margin: "auto",
        padding: "20px",
        fontFamily: "Arial",
      }}
    >
      <h1 style={{ textAlign: "center" }}>
        🎓 Student Management System
      </h1>

      <div
        style={{
          padding: "20px",
          border: "1px solid #ddd",
          borderRadius: "10px",
          marginBottom: "30px",
        }}
      >
        <h2>Add Student</h2>

        <input
          type="text"
          placeholder="Enter Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          style={{
            width: "100%",
            padding: "10px",
            marginBottom: "10px",
          }}
        />

        <input
          type="number"
          placeholder="Enter Age"
          value={age}
          onChange={(e) => setAge(e.target.value)}
          style={{
            width: "100%",
            padding: "10px",
            marginBottom: "10px",
          }}
        />

        <input
          type="text"
          placeholder="Enter Course"
          value={course}
          onChange={(e) => setCourse(e.target.value)}
          style={{
            width: "100%",
            padding: "10px",
            marginBottom: "10px",
          }}
        />

        <button
          onClick={addStudent}
          style={{
            backgroundColor: "#4CAF50",
            color: "white",
            border: "none",
            padding: "10px 20px",
            borderRadius: "5px",
            cursor: "pointer",
          }}
        >
          {editId ? "Update Student" : "Add Student"}
        </button>
      </div>

      <h2>Students List</h2>

      {students.length === 0 ? (
        <p>No Students Found</p>
      ) : (
        students.map((student) => (
          <div
            key={student._id}
            style={{
              border: "1px solid #ddd",
              borderRadius: "10px",
              padding: "15px",
              marginBottom: "15px",
              boxShadow: "0px 2px 5px rgba(0,0,0,0.1)",
            }}
          >
            <h3>{student.name}</h3>
            <p>Age: {student.age}</p>
            <p>Course: {student.course}</p>

            <button
              onClick={() => editStudent(student)}
              style={{
              backgroundColor: "blue",
              color: "white",
              border: "none",
              padding: "8px 15px",
              borderRadius: "5px",
              cursor: "pointer",
              marginRight: "10px",
          }}
>
  Edit
</button>
            <button
              onClick={() => deleteStudent(student._id)}
              style={{
                backgroundColor: "red",
                color: "white",
                border: "none",
                padding: "8px 15px",
                borderRadius: "5px",
                cursor: "pointer",
              }}
            >
              Delete
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default App;