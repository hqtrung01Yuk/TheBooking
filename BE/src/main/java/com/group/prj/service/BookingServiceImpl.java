package com.group.prj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.prj.exception.InvalidBookingRequestException;
import com.group.prj.exception.ResourceNotFoundException;
import com.group.prj.model.BookedRoom;
import com.group.prj.model.Room;
import com.group.prj.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final IRoomService roomService;

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No booking found with booking code" + confirmationCode));
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new InvalidBookingRequestException("Check-in data must come before check-out date");
        }

        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest, existingBookings);

        if (roomIsAvailable == true) {
            room.addBooking(bookingRequest);
            System.out.println("Total: " + bookingRequest.getTotalNumberOfGuest()); // Debug
            // System.out.println("Number Of Adults: " +
            // bookingRequest.getNumberOfAdults());
            bookingRepository.save(bookingRequest);
        } else {
            throw new InvalidBookingRequestException("Sorry. This room is not available for the selected dates");
        }

        return bookingRequest.getBookingConfirmationCode();
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking -> bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                        || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                        || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
                        || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
                        || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate())));
    }

    @Override
    public void cancleBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

}
