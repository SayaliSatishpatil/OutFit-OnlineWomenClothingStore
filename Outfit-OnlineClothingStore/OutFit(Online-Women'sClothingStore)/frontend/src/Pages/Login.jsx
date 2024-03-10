import React, { useContext, useState } from "react";
import "../Pages/CSS/LoginSignup.css";
import { ShopContext } from "../Context/ShopContext";
import { URL } from "../Constant/config";
import { useNavigate } from "react-router-dom";
const Login = () => {
  const { userLoggedIn,
    role,
    userLoggedOut,
    getTotalCartItems,
    getTotalCartAmount,
    all_product,
    cartItems,
    removeFromCart,
    addToCart,
    fetchOrders} = useContext(ShopContext);

  const [formData, setFormData] = useState({
    password: "",
    email: "",
  });
  const navigate =useNavigate()
  const changeHandler = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const login = async () => {
    console.log("Login Function Executed", formData);
    await fetch(`${URL}/user/token/login`, {
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
          // userLoggedIn(data?.role);
          userLoggedIn("user");
          localStorage.setItem("auth-token", data.data.accessToken);
          navigate("/womens")
          alert("Login Successful");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };

  return (
    <div className="loginsignup">
      <div className="loginsignup-container">
        <h1>Log In</h1>
        <div className="loginsignup-fields">
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
            login();
          }}
        >
          Continue
        </button>

        <p className="loginsignup-login">
          Create an account?{" "}
          <span
            onClick={() => {
              navigate("/signup")
              // window.location.replace("/signup");
            }}
          >
            SignUp Here
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

export default Login;
