import React, { useContext, useEffect, useState } from "react";
import "../Pages/CSS/ShopCategory.css";
// import { ShopContext } from "../Context/ShopContext";
import Item from "../Components/Item/Item";
import { URL } from "../Constant/config";
import { CATEGORIES } from "../Constant/category";
import { getIdByName, getNameById } from "../Utils/utils";

const ShopCategory = (props) => {
  const [search, setSearch] = useState("");
  const [filterBy, setfilterBy] = useState("categoryId");
  // const { all_product } = useContext(ShopContext);
  const [products, setProducts] = useState([]);

  const searchHandler = () => {
    if (search === "") {
      getProducts();
    }
    let filteredProducts;
    filteredProducts = products.filter((product) => {
      if (filterBy === "categoryId") {
        return product[`${filterBy}`] === getIdByName(search, CATEGORIES);
      } else if (filterBy === "price") {
        return product[`${filterBy}`] === parseInt(search);
      } else {
        return product[`${filterBy}`] === search;
      }
    });
    setProducts(filteredProducts);
  };
  const getProducts = async () => {
    await fetch(`${URL}/product/products`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        // Authorization: "Bearer " + localStorage.getItem("auth-token"),
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          setProducts(data.data);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  console.log(filterBy);
  useEffect(() => {
    getProducts();
  }, []);
  return (
    <div className="shop-category">
      <img className="shopcategory-banner" src={props.banner} alt="" />
      <div className="shopcategory-indexSort">
        <div className="shopcategory-sort">
          Search by
          <input
            type="radio"
            id="html"
            name="filter"
            value="categoryId"
            onChange={(e) => {
              setfilterBy(e.target.value);
            }}
          />
          <label for="html">Category</label>
          <input
            type="radio"
            id="css"
            name="filter"
            value="brand"
            onChange={(e) => {
              setfilterBy(e.target.value);
            }}
          />
          <label for="css">Brand</label>
          <input
            type="radio"
            id="javascript"
            name="filter"
            value="price"
            onChange={(e) => {
              setfilterBy(e.target.value);
            }}
          />
          <label for="javascript">Price</label>
        </div>
        <div className="shopcategory-search">
          <input
            placeholder="Search"
            style={{
              width: "300px",
              fontSize: "medium",
              padding: "15px",
              borderRadius: "20px",
              borderWidth: "1px",
            }}
            onChange={(event) => {
              setSearch(event.target.value);
            }}
          ></input>
          <button
            style={{
              marginLeft: "10px",
              fontSize: "medium",
              padding: "15px",
              borderRadius: "20px",
              borderWidth: "1px",
            }}
            onClick={searchHandler}
          >
            Search
          </button>
        </div>
      </div>
      <div className="shopcategory-products">
        {products.map((item) => {
          return (
            <Item
              key={item.id}
              id={item.id}
              name={item.name}
              brand={item.brand}
              category={getNameById(item.categoryId, CATEGORIES)}
              image={item.imageUrl}
              new_price={item.price}
            />
          );
        })}
      </div>
      <div className="shopcategory-loadmore">Explore more</div>
    </div>
  );
};

export default ShopCategory;
