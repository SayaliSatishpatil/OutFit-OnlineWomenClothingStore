import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ProductDisplay from "../Components/ProductDisplay/ProductDisplay";
import DescriptionBox from "../Components/DescriptionBox/DescriptionBox";
import RelatedProducts from "../Components/RelatedProducts/RelatedProducts";
import { URL } from "../Constant/config";
import { getNameById } from "../Utils/utils";
import { CATEGORIES } from "../Constant/category";

const Product = () => {
  const { productId } = useParams();
  const [productDetail, setProductDetail] = useState({});
  // const [category,setcategory]=useState('Top')
  const getProductDetails = async () => {
    await fetch(`${URL}/product/${productId}`, {
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
          setProductDetail({
            ...data.data,
            category: getNameById(data.data.categoryId, CATEGORIES),
          });
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };

  useEffect(() => {
    getProductDetails();
  }, []);
  return (
    <div>
      <ProductDisplay product={productDetail} productId={productId} />
      <DescriptionBox productId={productId} />
      <RelatedProducts />
    </div>
  );
};

export default Product;
