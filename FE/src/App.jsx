import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import ExistingRooms from "./components/room/ExistingRooms";
import { Routes, Route } from "react-router-dom";
import Home from "./components/home/Home";
import EditRoom from "./components/room/EditRoom";
import AddRoom from "./components/room/AddRoom";
import NavBar from "./components/layout/NavBar";
import Footer from "./components/layout/Footer";
import RoomListing from "./components/room/RoomListing";
import Admin from "./components/admin/Admin";
import CheckOut from "./components/booking/CheckOut";
import BookingSuccess from "./components/booking/BookingSuccess";
import BookingSummary from "./components/booking/BookingSummary";
import Bookings from "./components/booking/Bookings";
import FindBooking from "./components/booking/FindBooking";

function App() {
  return (
    <>
      <main>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/edit-room/:roomId" element={<EditRoom />} />
          <Route path="/existing-rooms" element={<ExistingRooms />} />
          <Route path="/booking-success" element={<BookingSuccess />} />
          <Route path="/add-room" element={<AddRoom />} />
          <Route path="/book-room/:roomId" element={<CheckOut />} />
          <Route path="/browse-all-rooms" element={<RoomListing />} />
          <Route path="/booking-summary" element={<BookingSummary />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/existing-bookings" element={<Bookings />} />
          <Route path="/find-booking" element={<FindBooking />} />
        </Routes>
        <Footer />
      </main>
    </>
  );
}

export default App;
