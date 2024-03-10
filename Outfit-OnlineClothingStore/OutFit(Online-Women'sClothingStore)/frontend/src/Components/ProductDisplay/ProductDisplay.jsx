import React, { useContext } from "react";
import "../ProductDisplay/ProductDisplay.css";
import star_icon from "../Assets/star_icon.png";
import star_dull_icon from "../Assets/star_dull_icon.png";
import { ShopContext } from "../../Context/ShopContext";
import { URL } from "../../Constant/config";
import p1_img from "../Assets/product_1.png";
// import { getNameById } from "../../Utils/utils";

const ProductDisplay = (props) => {
  const { product, productId } = props;
  console.log(product);
  const { addToCart, role } = useContext(ShopContext);
  console.log(role);
  const addCart = async () => {
    await fetch(`${URL}/cart/add/${productId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("auth-token"),
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          addToCart(productId);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  return (
    <div className="productdisplay">
      <div className="productdisplay-left">
        <div className="productdisplay-img-list">
          <img src={p1_img} alt="" />
          <img src={p1_img} alt="" />
          <img src={p1_img} alt="" />
          <img src={p1_img} alt="" />
        </div>
        <div className="productdisplay-img">
          <img className="productdisplay-main-img" src={p1_img} alt="" />
        </div>
      </div>
      <div className="productdisplay-right">
        <h1>{product?.name ? product?.name : "Model"}</h1>

        <div className="productdisplay-right-stars">
          <img src={star_icon} alt="" />
          <img src={star_icon} alt="" />
          <img src={star_icon} alt="" />
          <img src={star_icon} alt="" />
          <img src={star_dull_icon} alt="" />
          {/* <p>(122)</p> */}
        </div>
        <div className="productdisplay-right-prices">
          <div className="productdisplay-right-price-old">Rs. 1000</div>
          <div className="productdisplay-right-price-new">
            Rs. {product?.price ? product?.price : "900"}
          </div>
        </div>
        <div
          className="productdisplay-right-description"
          style={{ minHeight: "100px", maxHeight: "100px", overflow: "auto" }}
        >
          {product?.description
            ? product?.description
            : "asdasdadada sadadasdasd asdasdsadadsa sada"}
        </div>
        <div className="productdisplay-right-size">
          <h1>Select Size</h1>
          <div className="productdisplay-right-sizes">
            <div>S</div>
            <div>M</div>
            <div>L</div>
            <div>XL</div>
            <div>XXL</div>
          </div>
        </div>
        <button
          onClick={() => {
            if (localStorage.getItem("auth-token")) {
              addCart();
            } else {
              alert("You need to logIn to add product into cart");
            }
          }}
        >
          ADD TO CART
        </button>
        <p className="productdisplay-right-category">
          <span>Category : </span>
          {product?.category ? product?.category : 'T-shirt'}
        </p>
        <p className="productdisplay-right-category">
          <span>Brand : </span>
          {product?.brand ? product?.brand : "Nike"}
        </p>
      </div>
    </div>
  );
};

export default ProductDisplay;
