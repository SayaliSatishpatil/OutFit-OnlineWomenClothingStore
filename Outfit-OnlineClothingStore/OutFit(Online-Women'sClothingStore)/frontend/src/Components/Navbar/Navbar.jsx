import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";
import logo from "../Assets/logo.png";
import cart_icon from "../Assets/cart_icon.png";
import { ShopContext } from "../../Context/ShopContext";

const Navbar = () => {
  const [menu, setMenu] = useState("shop");
  const { cartItems, userLoggedOut } = useContext(ShopContext);
  return (
    <div className="navbar">
      <div className="nav-logo">
        <img src={logo} alt="" />
        <p
          onClick={() => {
            setMenu("shop");
          }}
        >
          <Link style={{ textDecoration: "none" }} to="/">
            OutFits
          </Link>
          {/* {menu === "shop" ? <hr /> : <></>} */}
        </p>
      </div>
      <ul className="nav-menu">
        {/* <li onClick={() => { setMenu("shop") }}><Link style={{textDecoration:'none'}} to='/'>Shop</Link>{menu === "shop" ? <hr /> : <></>}</li> */}
        {/* <li onClick={() => { setMenu("mens") }}><Link style={{textDecoration:'none'}} to='/mens'>Men</Link>{menu === "mens" ? <hr /> : <></>}</li> */}
        <li
          onClick={() => {
            setMenu("womens");
          }}
          style={{ marginLeft: "200px" }}
        >
          <Link style={{ textDecoration: "none" }} to="/womens">
            Explore
          </Link>
          {menu === "womens" ? <hr /> : <></>}
        </li>
      </ul>
      <div className="nav-login-cart">
        <Link style={{ textDecoration: "none" }} to="/order">
          <button> My Order</button>
        </Link>

        {localStorage.getItem("auth-token") ? (
          <button
            onClick={() => {
              localStorage.removeItem("auth-token");
              userLoggedOut();
              window.location.replace("/");
            }}
          >
            Logout
          </button>
        ) : (
          <Link style={{ textDecoration: "none" }} to="/login">
            <button>Login</button>
          </Link>
        )}

        <Link style={{ textDecoration: "none" }} to="/cart">
          <img src={cart_icon} alt="" />
        </Link>
        <div className="nav-cart-count">{cartItems}</div>
      </div>
    </div>
  );
};

export default Navbar;
