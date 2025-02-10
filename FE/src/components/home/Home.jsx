import HotelService from "../common/HotelService";
import Parallax from "../common/Parallax";
import RoomCarousel from "../common/RoomCarousel";
import RoomSearch from "../common/RoomSearch";
import HeaderMain from "../layout/MainHeader";

const Home = () => {
  return (
    <section>
      <HeaderMain />

      <section className="container">
        <RoomSearch />

        <RoomCarousel />

        <Parallax />

        <RoomCarousel />

        <HotelService />

        <Parallax />

        <RoomCarousel />
      </section>
    </section>
  );
};

export default Home;
