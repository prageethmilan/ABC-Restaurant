import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  CardText,
  CardLink, Row, Col, Label, Form, Button
} from "reactstrap"
import MultiCarouselSlides from "@src/views/home/MultiCarouselSlides"
// import CarouselAutoPlay from "@src/views/home/CarouselAutoPlay"
import './style.scss'
import Select from "react-select"
import { useNavigate } from "react-router-dom"
import SwiperCore, { Autoplay } from 'swiper'
// import Gallery from "@src/views/home/gallery/Gallery"
import Footer from "@src/views/home/footer/footer"
import { useRTL } from "@hooks/useRTL"
import { useState } from "react"
import logo from '@src/assets/images/logo/logo.png'
import CarouselAutoPlay from "@src/views/home/CarouselAutoPlay";


SwiperCore.use([Autoplay])
const Home = () => {

  const [isRtl] = useRTL()
  const navigate = useNavigate()

  const [form, setForm] = useState({
    tag: "", minscore: "", maxscore: ""
  })

  const categoriesSingle = [
    { value: "Colombo", label: "Colombo" },
    { value: "Negombo", label: "Negombo" },
    { value: "Kandy", label: "Kandy" },
    { value: "Galle", label: "Galle" },
    { value: "Mathara", label: "Mathara" },
    { value: "Nuwaraeliya", label: "Nuwaraeliya" },
    { value: "Rathnapura", label: "Rathnapura" }
  ]

  const handleCategoryChange = (selectedTagType) => {
    setForm((prev) => ({
      ...prev, tag: selectedTagType.value
    }))
  }

  const apiHandlerForSearch = () => {
    // if (validatePlaceSearchDetails(form)) {

      /*     searchPlaceByTag_Min_Max(createPlaceForSearch(form))
             .then((response) => {
               if (response.data) {
                 navigate(PLACES_PATH_FILTER, {
                   state: { searchData: response.data.data } // Pass search results as state
                 })
               }
             })
             .catch((error) => {
               console.error("API Request Error:", error.message)
             })*/
    // }
  }

  return (
    <div className={"home_page"}>
      <div className="container-fluid banner-background">
        <Row className={"pt-5"}>
          <small className={'text-white text-center'}>DISCOVER THE SPICY MEALS EXPERIENCE, A CULINARY JOURNEY</small>
          <h1 className={"text-center text-white fw-bold"} style={{ fontSize: "7rem", fontWeight: "600" }}>Spicy
            Meals</h1>
          <h2 className={"text-center text-white"}> Let's Enjoy Favorite Restaurant Experience. </h2>
        </Row>
        <Card className="custom-card">
          <h2 className={"text-center form_head mt-1"} style={{ fontWeight: 800 }}>Make Reservation</h2>
          <hr className={'me-2 ms-2'} />
          <CardBody>
            <Form className="form" onSubmit={e => e.preventDefault()}>
              <Row>
                <Col sm="12" className="mb-2">
                  <Label className="form-label" style={{ color: '#ecda13' }} for="input-name">
                    Select Your Branch
                  </Label>
                  <Select
                    id={`categoryTag`}
                    className="react-select"
                    classNamePrefix="select"
                    value={categoriesSingle.find(item => item.value === form.tag)}
                    isClearable={false}
                    options={categoriesSingle}
                    onChange={handleCategoryChange}
                  />
                </Col>

                <Col className="d-grid pt-1" sm="12">
                  <Button
                    className="btn_sch"
                    color="warning"
                    onClick={apiHandlerForSearch}
                  >
                    Make a Reservation</Button>
                </Col>
              </Row>
            </Form>
          </CardBody>
        </Card>
      </div>

      {/* INTRODUCTION PAGE  */}
      <div className={"container-fluid bg-white p-4"} style={{ height: "90vh" }}>
        <Row className={" pt-4 pb-2 introduction_page"}>
          <Col md={5} sm={12} lg={5} className={"m-0 p-0"}>

            <h2 className={"text-start"}><span className="script">Welcome To Spicy Meals!</span><br />
              Who are we?</h2>
            <img src={logo} alt={"travel logo"} style={{ width: "20vw" }} />
          </Col>
          <Col md={7} sm={12} lg={7} className={"mt-1"}>
            <div className={"paragraph-wrap  "}>

              <h3 className={""} style={{ color: "#DD673F", fontWeight: 700 }}>Discover the Spicy Foods Experience, A
                Culinary Journey</h3><br />

              Welcome to Spicy Foods – Where Every Meal is a Celebration! At Spicy Meals, we go beyond merely serving
              food we craft experiences that linger in your memory. Our commitment is to be more than just a restaurant
              – we
              want to be a destination where joy, warmth, and deliciousness come together.
              Founded with the vision of becoming the largest restaurant chain in Sri Lanka, Spicy Meals is the
              brainchild of a team dedicated to redefining the dining experience.<br /><br />

              <h3 className={""} style={{ color: "#DD673F", fontWeight: 700 }}>Our Culinary Offering: A Symphony of
                Flavors</h3><br />
              Dive into a world of culinary excellence with our diverse menu featuring Western,
              Chinese, Indian, Italian, Sri Lankan, and Street Food delights. Each dish is a testament to our
              commitment to serving fresh, delicious, and mouthwatering varieties of quality foods, prepared
              with meticulous attention to high industry standards and hygiene.<br /><br />

              <h3 className={""} style={{ color: "#DD673F", fontWeight: 700 }}>Your Destination for Joyful Memories</h3>
              <br />
              Beyond the tantalizing tastes and aromas, Spicy Meals is a place where memories are made.
              Our team is not just here to serve; we're here to create moments of joy that extend beyond
              the dining table. Every visit to Spicy Meals is an opportunity to escape the ordinary
              and immerse yourself in a culinary experience like no other.

            </div>
          </Col>
        </Row>
      </div>

      {/* MAIN Facilities PAGE  */}
      <div className={" main_category"}>
        <h1 className={"p-4 text-center main_sub_header"}>Explore Our main Facilities</h1>
        <MultiCarouselSlides isRtl={isRtl} />
      </div>

      {/* Special Offers PAGE  */}
      <div className={"container-fluid about_sriLanka bg-light-warning "}>
        <Row>
          <h1 className={"pt-4 pb-4 text-center main_sub_header"}>What is Special Offers ?</h1>
          <Col md={5} className={"ps-2 pe-2"}>

            <h3 style={{ color: "#DD673F", fontWeight: 700, fontSize: '26px' }} className={"text-start pb-2 pt-1"}>
              <span className="script text-dark">Indulge in Our Latest Offers and Flavors!</span></h3><br />

            <p style={{ fontSize: '15px' }} className={'text-dark'}>At Spicy Meals, we are dedicated to providing a
              dynamic and exciting dining experience with our
              ever-changing menu. To keep your dining experience fresh and full of surprises, we update our meal
              offerings weekly. Each week, our chefs curate a selection of new and seasonal dishes to ensure that
              there is always something new and delicious to try.

              <br /><br />
              From innovative appetizers to delectable main courses, our weekly offers feature a diverse range of
              flavors and ingredients, reflecting the best of what’s in season. Whether you’re a regular guest or
              visiting us for the first time, our rotating menu ensures that every visit offers a unique culinary
              adventure.
              <br /><br />
              <span className={"fw-bold"}>Don’t miss out on our weekly specials each meal is crafted to deliver exceptional taste and quality,
                making every visit to Spicy Meals a delightful experience.</span></p>
          </Col>
          <Col md={7}>
            <Col sm="12">
              <CarouselAutoPlay isRtl={isRtl} />
            </Col>
          </Col>
        </Row>
      </div>

      {/* Gallery */}

      <div className={"container-fluid  bg-white "}>
        <Row>
          <h1 className={"pt-4 pb-4 text-center main_sub_header"}>Experience Culinary Elegance</h1>
        </Row>
        <Row>
          <Gallery />
        </Row>
      </div>


      {/* MAP LOCATION PAGE  */}
      <div className={"container-fluid map_page bg-white mt-5 pt-5"}>
        <Row>
          <Col md={7} className={"map_heading d-flex justify-content-center align-items-start"}>
            <div>
              <Row>
                <Col>
                  <h2 className={"map_page_title text-center"}>Our Main Branches in our Restaurant Chain</h2>
                </Col>
              </Row>
              <Row>
                <Col>
                  <p className={"mt-2  map_desc "}>
                    Explore our main branches and the excellent facilities we offer at each location. Whether you are
                    looking for fine dining, comfortable accommodation, or event hosting, we have you covered. Discover
                    the best
                    of our services at each branch and make your visit memorable. Our branches are strategically located
                    across the country to ensure you have convenient access to our top-notch services.
                    <br />
                    <br />
                    Each branch is equipped with state-of-the-art facilities, professional staff, and a welcoming
                    atmosphere that guarantees a
                    remarkable experience. Come and enjoy the culinary delights, relax in our luxurious accommodations,
                    and let us
                    help you host your special events with elegance and style. Join us at any of our branches and
                    experience
                    the excellence we are renowned for.
                  </p>
                </Col>
              </Row>
            </div>
          </Col>
          <Col md={5}>
            {/*<iframe*/}
            {/*  src="https://www.google.com/maps/d/embed?mid=1H1ADd1djKllgjsCH1zUtYLqyS8JTDK4&ehbc=2E312F&z=7"*/}
            {/*  width="100%"*/}
            {/*  height="550px">*/}
            {/*</iframe>*/}
          </Col>
        </Row>
      </div>
      <Footer />
    </div>
  )
}

export default Home
