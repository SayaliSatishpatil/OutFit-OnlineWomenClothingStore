import React, { createContext, useState } from "react";
import all_product from "../Components/Assets/all_product";

export const ShopContext = createContext(null);

export const getDefaultCart = () => {
  let cart = {};
  for (let index = 0; index < all_product.length + 1; index++) {
    cart[index] = 0;
  }
  return cart;
};

const ShopContextProvider = (props) => {
  const [cartItems, setCartItems] = useState(0);
  const [cartOrder, setCartOrder] = useState();
  const [totalAmount, setTotalAmount] = useState(0);
  const [role, setRole] = useState(null);
  const [order, setOrder] = useState(null);
  const userLoggedIn = (role) => {
    setRole(role);
    console.log(role);
  };
  const userLoggedOut = () => {
    setRole(null);
    console.log(role);
  };

  const fetchOrders = (order) => {
    setOrder(order);
    console.log(order);
  };

  const addToCart = () => {
    setCartItems((prev) => prev + 1);
    console.log(cartItems);
  };

  const removeFromCart = () => {
    setCartItems((prev) => prev + 1);
  };

  const setTotalCartItems = (count) => {
    setCartItems(count);
  };

  const contextValue = {
    userLoggedIn,
    role,
    userLoggedOut,
    setTotalCartItems,
    all_product,
    cartItems,
    removeFromCart,
    addToCart,
    fetchOrders,
    order,
    cartOrder,
    setCartOrder,
    setTotalAmount,
    totalAmount
  };
  return (
    <ShopContext.Provider value={contextValue}>
      {props.children}
    </ShopContext.Provider>
  );
};

export default ShopContextProvider;
