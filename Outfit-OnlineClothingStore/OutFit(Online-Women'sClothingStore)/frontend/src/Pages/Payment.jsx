import React, { useContext, useState } from "react";
import "../Pages/CSS/LoginSignup.css";
import { ShopContext } from "../Context/ShopContext";
import { URL } from "../Constant/config";
import { useNavigate } from "react-router-dom";

const Payment = () => {
  const [address, setAddress] = useState("");
  // const { cartOrder } = useContext(ShopContext);
  const { totalAmount, cartOrder } = useContext(ShopContext);
  const navigate = useNavigate();
  const onPayment = async () => {
    cartOrder.map((item) => {
      placeOrder(item.id);
    });
    navigate("/order");
    alert("Order Successful");
  };
  const placeOrder = async (cartId) => {
    await fetch(`${URL}/order/place/${cartId}/${address}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("auth-token"),
      },
      // body: JSON.stringify({
      //   cartId: cartId,
      //   address: address,
      // }),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  return (
    <div className="loginsignup">
      <div className="loginsignup-container">
        <h1>Payment</h1>
        <div className="loginsignup-fields">
          <input
            name="address"
            value={address}
            onChange={(event) => {
              setAddress(event.target.value);
            }}
            type="email"
            placeholder="Add Address"
            required="true"
          />
          <h2>Total Amount</h2>
          <h3>Rs. {totalAmount}</h3>
        </div>
        <button
          onClick={() => {
            onPayment();
          }}
        >
          Place Order
        </button>
      </div>
    </div>
  );
};

export default Payment;
