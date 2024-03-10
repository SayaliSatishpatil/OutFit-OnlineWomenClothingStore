import { URL } from "../Constant/config";

export const getProducts = async () => {
  await fetch(`${URL}/product/products`, {
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
        return data.data;
      }
    })
    .catch((error) => {
      console.log(error);
      alert("Login error");
    });
};


export const getCart = async () => {
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
        return data.data
      }
    })
    .catch((error) => {
      console.log(error);
      alert("Login error");
    });
};
