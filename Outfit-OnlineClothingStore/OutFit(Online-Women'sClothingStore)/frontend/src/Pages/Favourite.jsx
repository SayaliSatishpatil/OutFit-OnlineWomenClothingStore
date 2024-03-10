import React, { useContext, useEffect, useState } from "react";
import "./CSS/Order.css";
import { ShopContext } from "../Context/ShopContext";
import { Link } from "react-router-dom";
// import remove_icon from "../Components/Assets/cart_cross_icon.png";

const Favourite = () => {
  const { getTotalCartAmount, all_product, cartItems, removeFromCart } =
    useContext(ShopContext);
  console.log(cartItems);
  const [favourite, setFavourite] = useState(all_product);
  console.log(cartItems);
  const getFavourite = async () => {
    await fetch(`${URL}/order/orders`, {
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
          setFavourite(data.data);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  const removeFavourite = async (orderId) => {
    await fetch(`${URL}/order/${orderId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("auth-token"),
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          getFavourite();
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  useEffect(() => {
    getFavourite();
  }, []);
  return (
    <div className="cartitems">
      <h1 style={{ textAlign: "center", marginBottom: "30px" }}>Favourites</h1>
      <div className="cartitems-format-main">
        <p>Products</p>
        <p>Title</p>
        <p>Category</p>
        <p>Brand</p>
        <p>Price</p>
        <p>Remove</p>
      </div>
      <hr />
      {favourite.map((e) => {
        return (
          <div>
            <div className="cartitems-format cartitems-format-main">
              <Link to={`/product/${e.id}`}>
                <img src={e.image} alt="" className="carticon-product-icon" />
              </Link>
              <p>{e.name}</p>
              <p>Rs. {e.new_price}</p>
              <p>{e.quantity} pc</p>
              <p>{e.status}</p>

              <button
                style={{
                  borderRadius: "20px",
                  height: "40px",
                  width: "120px",
                }}
                onClick={() => {
                  removeFavourite();
                }}
              >
                Cancel
              </button>
            </div>
            <hr />
          </div>
        );
      })}
    </div>
  );
};

export default Favourite;
