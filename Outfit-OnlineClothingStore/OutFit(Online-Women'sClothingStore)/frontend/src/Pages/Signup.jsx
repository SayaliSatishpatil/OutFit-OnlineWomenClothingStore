import React, { useContext, useState } from "react";
import "../Pages/CSS/LoginSignup.css";
import { URL } from "../Constant/config";
import { useNavigate } from "react-router-dom";
// import { ShopContext } from "../Context/ShopContext";
const Signup = () => {
  //   const { userLoggedIn } = useContext(ShopContext);

  const [formData, setFormData] = useState({
    username: "",
    age: "",
    password: "",
    email: "",
  });
  const navigate = useNavigate();
  const changeHandler = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const signup = async () => {
    console.log("Signup Function Executed", formData);
    await fetch(`${URL}/user/create`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          navigate("/login");
          // window.location.replace("/login");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
        return;
      });
  };

  return (
    <div className="loginsignup">
      <div className="loginsignup-container">
        <h1>Sign Up</h1>
        <div className="loginsignup-fields">
          <input
            name="username"
            value={formData.username}
            onChange={changeHandler}
            type="text"
            placeholder="Your Name"
            required="true"
          />
          <input
            name="age"
            value={formData.age}
            onChange={changeHandler}
            type="number"
            placeholder="Your Age"
            required="true"
          />

          <input
            name="email"
            value={formData.email}
            onChange={changeHandler}
            type="email"
            placeholder="Email Address"
            required="true"
          />
          <input
            name="password"
            value={formData.password}
            onChange={changeHandler}
            type="Password"
            placeholder="Password"
            required="true"
          />
        </div>
        <button
          onClick={() => {
            signup();
          }}
        >
          Continue
        </button>

        <p className="loginsignup-login">
          Already have an account?{" "}
          <span
            onClick={() => {
              navigate("/login");
            }}
          >
            Login Here
          </span>
        </p>

        <div className="loginsignup-agree">
          <input type="checkbox" name="" id="" />
          <p>By continuing, i agree to the terms of use & privacy policy</p>
        </div>
      </div>
    </div>
  );
};

export default Signup;
