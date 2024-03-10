import React, { useContext, useEffect, useState } from "react";
import "../CartItems/CartItems.css";
import { ShopContext } from "../../Context/ShopContext";
import remove_icon from "../Assets/cart_cross_icon.png";
import { Link, useNavigate } from "react-router-dom";
import { URL } from "../../Constant/config";
import p1_img from "../Assets/product_1.png";
const CartItems = () => {
  const {
    setTotalCartItems,
    setTotalAmount,
    totalAmount,
    cartOrder,
    setCartOrder,
    removeFromCart,
  } = useContext(ShopContext);
  console.log(cartOrder);
  const [cart, setCart] = useState([]);
  // const [amount, setAmount] = useState([]);
  const navigate = useNavigate();
  console.log(cart);
  console.log(totalAmount)
  const removeCart = async (productId) => {
    await fetch(`${URL}/cart/items/${productId}`, {
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
          getCart();
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  const getCart = async () => {
    await fetch(`${URL}/cart/items`, {
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
          setCart((prev) => [...prev, ...data.data]);
          let count = 0;
          let totalPrice = 0;
          data.data.map((item) => {
            totalPrice += (parseInt(item.quantity) * parseInt(item.product.price))
            console.log(totalPrice)
            count += parseInt(item.quantity);
            console.log(count);
          });
          setTotalCartItems(count);
          setCartOrder(data.data);
          setTotalAmount(totalPrice);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  useEffect(() => {
    getCart();
  }, []);
  return (
    <div className="cartitems">
      <h1 style={{ textAlign: "center", marginBottom: "30px" }}>My Cart</h1>
      <div className="cartitems-format-main">
        <p>Products</p>
        <p>Title</p>
        <p>Price</p>
        <p>Quantity</p>
        <p>Total</p>
        <p>Remove</p>
      </div>
      <hr />
      {cart.map((e) => {
        return (
          <div>
            <div className="cartitems-format cartitems-format-main">
              <Link to={`/product/${e.id}`}>
                <img src={p1_img} alt="" className="carticon-product-icon" />
              </Link>
              <p>{e.product.name}</p>
              <p>Rs. {e.product.price}</p>
              <p>{e.quantity}</p>
              <p>Rs. {parseInt(e.product.price) * parseInt(e.quantity)}</p>
              <img
                className="cartitems-remove-icon"
                src={remove_icon}
                onClick={() => {
                  removeFromCart();
                  removeCart(e.productId);
                }}
                alt=""
              />
            </div>
            <hr />
          </div>
        );
      })}

      <div className="cartitems-down">
        <div className="cartitems-total">
          <h1>Cart Totals</h1>
          <div>
            <div className="cartitems-total-item">
              <p>Subtotal</p>
              <p>Rs. {totalAmount}</p>
            </div>
            <hr />
            <div className="cartitems-total-item">
              <p>Shipping Fee</p>
              <p>Free</p>
            </div>
            <hr />
            <div className="cartitems-total-item">
              <h3>TOtal</h3>
              <h3>Rs. {totalAmount}</h3>
            </div>
          </div>
          <button
            onClick={() => {
              navigate("/payment");
            }}
          >
            Checkout
          </button>
        </div>
        <div className="cartitems-promocode">
          <p>If you have a promocode, Enter it here</p>
          <div className="cartitmes-promobox">
            <input type="text" placeholder="promoCode" />
            <button>Submit</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartItems;
