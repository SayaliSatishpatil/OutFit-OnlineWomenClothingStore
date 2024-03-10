import React from 'react'
import '../RelatedProducts/RelatedProducts.css'
import data_product from '../Assets/data'
import Item from '../Item/Item'

const RelatedProducts = () => {
  return (
    <div className='relatedproducts'>
      <h1>Related Products</h1>
      <hr />
      <div className='relatedproducts-item'>
        {data_product.map((item, i) => {
          return <Item key={i} id={1} image={item.image} />
        })}
      </div>
    </div>
  )
}

export default RelatedProducts
