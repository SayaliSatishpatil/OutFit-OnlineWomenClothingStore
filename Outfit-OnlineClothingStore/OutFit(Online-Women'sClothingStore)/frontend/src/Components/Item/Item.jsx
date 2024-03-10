import React from 'react'
import '../Item/Item.css'
import { Link } from 'react-router-dom'
import p1_img from '../Assets/product_1.png'
const Item = (props) => {
  return (
    <div className='item'>
      {props.name ? (
        <>
          <Link to={`/product/${props.id}`}>
            <img onClick={window.scrollTo(0, 0)} src={p1_img} alt='' />
          </Link>
          <p>
            <b>Name : </b>
            {props.name}
          </p>
          <p>
            <b>Brand : </b>
            {props.brand}
          </p>
          <p>
            <b>Category : </b>
            {props.category}
          </p>
          <div className='item-prices'>
            <div className='item-price-new'>Rs. {props.new_price}</div>
          </div>
        </>
      ) : (
        <img onClick={window.scrollTo(0, 0)} src={p1_img} alt='' />
      )}
    </div>
  )
}

export default Item
