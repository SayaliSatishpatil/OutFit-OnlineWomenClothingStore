import "./App.css";
import Navbar from "./Components/Navbar/Navbar";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Shop from "../src/Pages/Shop";

import ShopCategory from "../src/Pages/ShopCategory";
import Product from "../src/Pages/Product";
import Cart from "../src/Pages/Cart";
import Footer from "./Components/Footer/Footer";
import women_banner from "../src/Components/Assets/banner_women.png";
import Order from "./Pages/Order";
import Payment from "./Pages/Payment";
import Favourite from "./Pages/Favourite";
import Signup from "./Pages/Signup";
import Login from "./Pages/Login";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Shop />} />
          <Route
            path="/womens"
            element={<ShopCategory banner={women_banner} category="women" />}
          />
          <Route path="/product" element={<Product />}>
            <Route path=":productId" element={<Product />} />
          </Route>
          <Route path="/cart" element={<Cart />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/order" element={<Order />} />
          <Route path="/payment" element={<Payment />} />
          <Route path="/favourite" element={<Favourite />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
