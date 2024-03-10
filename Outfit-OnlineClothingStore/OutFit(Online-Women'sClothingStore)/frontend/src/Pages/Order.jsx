import React, { useEffect, useState } from "react";
import "./CSS/Order.css";
import { ShopContext } from "../Context/ShopContext";
import { Link } from "react-router-dom";
import { URL } from "../Constant/config";
import p1_img from "../Components/Assets/product_1.png";
import { getNameById } from "../Utils/utils";
import { STATUS } from "../Constant/status";

const Order = () => {
  const [order, setOrder] = useState([]);
  console.log(order);
  const getOrders = async () => {
    await fetch(`${URL}/order/user/orders`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("auth-token"),
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          setOrder(data.data.orderDtos);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  const cancelOrder = async (orderId) => {
    console.log(orderId);
    await fetch(`${URL}/order/cancel/${orderId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("auth-token"),
        // Access-Control-Allow-Origin: "http://localhost:3000"
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          getOrders();
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Order Cancel Error");
      });
  };
  useEffect(() => {
    getOrders();
  }, []);
  return (
    <div className="cartitems">
      <h1 style={{ textAlign: "center", marginBottom: "30px" }}>My Orders</h1>
      <div className="cartitems-format-main">
        <p>Products</p>
        <p>Title</p>
        {/* <p>Price</p> */}
        <p>Quantity</p>
        <p>Total</p>
        <p>Status</p>
        <p>Remove</p>
      </div>
      <hr />
      {order.map((e) => {
        console.log(e);
        return (
          <div>
            <div className="cartitems-format cartitems-format-main">
              <Link to={`/product/${e.product.id}`}>
                <img src={p1_img} alt="" className="carticon-product-icon" />
              </Link>
              <p>{e.product.name}</p>
              <p>{e.quantity} </p>
              <p>Rs. {parseInt(e.product.price) * parseInt(e.quantity)}</p>

              <p>{getNameById(e.status, STATUS)}</p>
              {getNameById(e.status, STATUS) === "placed" && (
                <button
                  style={{
                    borderRadius: "20px",
                    height: "40px",
                    width: "120px",
                  }}
                  onClick={() => {
                    cancelOrder(e.orderId);
                  }}
                >
                  Cancel
                </button>
              )}
            </div>

            <hr />
          </div>
        );
      })}
    </div>
  );
};

export default Order;
