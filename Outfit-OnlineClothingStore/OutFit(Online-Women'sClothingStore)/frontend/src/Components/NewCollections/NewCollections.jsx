import React from 'react'
import '../NewCollections/NewCollections.css'
import new_collection from '../Assets/new_collections'
import Item from '../Item/Item'

const NewCollections = () => {
  return (
    <div className='new-collections'>
      <h1>NEW COLLECTIONS</h1>
      <hr />
      <div className='collections'>
        {new_collection.map((item, i) => {
          return <Item key={i} id={1} image={item.image} />
        })}
      </div>
    </div>
  )
}

export default NewCollections
