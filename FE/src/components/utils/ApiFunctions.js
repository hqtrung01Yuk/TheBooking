/* eslint-disable no-unused-vars */
import axios from "axios";

// set to api
export const api = axios.create({
  baseURL: "http://localhost:8080",
});

// header to restapi
export const getHeader = (isFormData = false) => {
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  if (!isFormData) {
    headers["Content-Type"] = "application/json";
  }

  return headers;
};

/* This function adds a new room room to the database */
export const addRoom = async (photo, roomType, roomPrice) => {
  const formData = new FormData();
  formData.append("photo", photo);
  formData.append("roomType", roomType);
  formData.append("roomPrice", roomPrice);

  try {
    const response = await api.post("/rooms/add/new-room", formData);

    return response.data; // Trả về dữ liệu từ API
  } catch (error) {
    throw new Error(`Error: ${error.response?.status || error.message}`);
  }
};

/* This function gets all room types from thee database */
export async function getRoomTypes() {
  try {
    const response = await api.get("/rooms/room/types");

    return response.data;
  } catch (error) {
    throw new Error("Error fetching room types");
  }
}

// fetch all rooms from the database
export async function getAllRooms() {
  try {
    const result = await api.get("/rooms/all-rooms");

    return result.data;
  } catch (err) {
    throw new Error("Error fetching rooms");
  }
}

// fetch delete room by id
export async function deleteRoom(roomId) {
  try {
    const result = await api.delete(`/rooms/delete/room/${roomId}`);

    return result.data;
  } catch (err) {
    throw new Error(`Error deleting room ${err.message}`);
  }
}

// function update room
export async function updateRoom(roomId, roomData) {
  const formData = new FormData();
  formData.append("roomType", roomData.roomType);
  formData.append("roomPrice", roomData.roomPrice);
  formData.append("photo", roomData.photo);

  try {
    const response = await api.put(`/rooms/update/${roomId}`, formData, {
      headers: getHeader(true), // Không đặt Content-Type cho FormData
    });

    // console.log("✅ Update Room Response:", response);
    return response;
  } catch (error) {
    // console.error("❌ Error updating room:", error.response || error);
    console.error(error.message);
    throw error;
  }
}

// function gets room by Id
export async function getRoomById(roomId) {
  try {
    const result = await api.get(`/rooms/room/${roomId}`);

    return result.data;
  } catch (err) {
    throw new Error(`Error fetching room ${err.message}`);
  }
}

// function saves a new booking to the database
export async function bookRoom(roomId, booking) {
  try {
    const response = await api.post(
      `/bookings/room/${roomId}/booking`,
      booking
    );

    return response.data;
  } catch (err) {
    if (err.response && err.response.data) {
      throw new Error(err.response.data);
    } else {
      throw new Error(`Error booking room: ${err.message}`);
    }
  }
}

// function gets all booking from the database
export async function getAllBookings() {
  try {
    const result = await api.get("/bookings/all-bookings");

    return result.data;
  } catch (err) {
    throw new Error(`Error fetching bookings: ${err.message}`);
  }
}

// this function get booking by the confirmation code
export async function getBookingByConfirmationCode(confirmationCode) {
  try {
    const result = await api.get(`/bookings/confirmation/${confirmationCode}`);

    return result.data;
  } catch (err) {
    if (err.response && err.response.data) {
      throw new Error(`${err.response.data}`);
    } else {
      throw new Error(`Error find booking: ${err.message}`);
    }
  }
}

// function cancles booking
export async function cancleBooking(bookingId) {
  try {
    const result = await api.delete(`/bookings/booking/${bookingId}/delete`);

    return result.data;
  } catch (err) {
    throw new Error(`Error cancelling booking: ${err.message}`);
  }
}

// gets all availavle rooms from the database
export async function getAvailableRooms(checkInDate, checkOutDate, roomType) {
  const result = await api.get(
    `/rooms/available-rooms?checkInDate=${checkInDate}&checkOutDate=${checkOutDate}&roomType=${roomType}`
  );

  return result;
}
