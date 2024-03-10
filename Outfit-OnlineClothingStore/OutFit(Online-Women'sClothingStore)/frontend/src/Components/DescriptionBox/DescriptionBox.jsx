import React, { useEffect, useState } from "react";
import "../DescriptionBox/DescriptionBox.css";
import Product from "../../Pages/Product";
import { URL } from "../../Constant/config";

const DescriptionBox = (props) => {
  const { productId } = props;
  console.log(productId);
  const [review, setReview] = useState([]);
  const [comment, setComment] = useState("");
  const getReviews = async () => {
    await fetch(`${URL}/review/reviews/${productId}`, {
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
          setReview(data.data);
        }
      })
      .catch((error) => {
        console.log(error);
        alert("Login error");
      });
  };
  const addReview = async () => {
    await fetch(`${URL}/review/add`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("auth-token"),
      },
      body: JSON.stringify({
        productId: productId,
        comment: comment,
        rating: 4,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data?.success) {
          getReviews();
        }
      })
      .catch((error) => {
        console.log(error);
        alert("error");
      });
  };
  console.log(review);
  useEffect(() => {
    getReviews();
  }, []);
  return (
    <div className="descriptionbox">
      <div className="descriptionbox-navigator">
        {/* <div className="descriptionbox-nav-box">Description</div> */}
        <div className="descriptionbox-nav-box fade">Reviews </div>
      </div>
      <div className="descriptionbox-description">
        <div>
          {/* <h4 style={{ marginBottom: "10px" }}></h4> */}
          <input
            name="comment"
            value={comment}
            onChange={(e) => {
              setComment(e.target.value);
            }}
            type="text"
            placeholder="Add Comment"
            required="true"
            style={{
              height: "40px",
              width: "80%",
              paddingInline: "10px",
              borderRadius: "20px",
              fontSize: "medium",
            }}
          />
          <button
            style={{
              borderRadius: "20px",
              height: "40px",
              width: "150px",
              marginLeft: "30px",
              fontSize: "medium",
            }}
            onClick={() => {
              addReview();
            }}
          >
            Add Comment
          </button>
          {review.map((item) => {
            return (
              <div style={{ marginTop: "20px" }}>
                <h4 style={{ marginBottom: "10px" }}>{item.userName}</h4>
                <p>{item.comment}</p>
              </div>
            );
          })}

          <div style={{ marginTop: "20px" }}>
            <h4 style={{ marginBottom: "10px" }}>username1</h4>
            <p>This is a comment</p>
          </div>
          <div style={{ marginTop: "20px" }}>
            <h4 style={{ marginBottom: "10px" }}>username2</h4>
            <p>This is a comment</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DescriptionBox;
