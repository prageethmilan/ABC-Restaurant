import React, { Fragment, useEffect, useState } from "react"
import NavbarUser from "./NavbarUser"
import { FileText, Grid, Home, MapPin, PlusCircle } from "react-feather"
import { Col, Row } from "reactstrap"
import themeConfig from "@configs/themeConfig"
import { Link, useNavigate } from "react-router-dom"
import "../../../../main.scss"
import { routePathHandler } from "@store/routePath"
import { useDispatch } from "react-redux"
import toast from "react-hot-toast"


const ThemeNavbar = (props) => {
  const {} = props
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [userStatus, setUserStatus] = useState(localStorage.getItem("ISLOGIN"))
  // const windowPath = useSelector((state) => state.routePath.pathName)

  useEffect(() => {
    setUserStatus(localStorage.getItem("ISLOGIN"))
  }, [userStatus])


  const setWindowPathHandler = (path) => {
    dispatch(routePathHandler(path))
  }

  const handleAddNewPlaceClick = () => {
    if (!userStatus) {
      toast.success("You must register or sign in before making a reservation.")
      navigate("/login")
    } else if (userStatus === "CUSTOMER") {
      setWindowPathHandler('/reservation-form')
    }
  }


  return (<Fragment>
      <Row className="bookmark-wrapper d-flex align-items-center" style={{ width: userStatus ? "79vw" : "100vw" }}>
        {/*     <Col className="navbar-nav d-xl-none">
          <NavItem className="mobile-menu me-auto">
            <NavLink className="nav-menu-main menu-toggle hidden-xs is-active" onClick={() => setMenuVisibility(true)}>
              <Menu className="ficon" />
            </NavLink>
          </NavItem>
        </Col>*/}

        <Col md={3} className={"nav-logo-wrapper d-flex justify-content-start align-items-center"}>
          <Row>
            <Col md={3}>
              <Link to={'/home'} className="navbar-brand">
              <span className="brand-logo">
                <img src={themeConfig.app.appLogoImage} width={150}  alt="logo" />
              </span>
              </Link>
            </Col>
            {/*<Col md={9} style={{ marginTop: "8px" }}>*/}
            {/*  <h2 className="brand-text  m-0 p-0" style={{ color: '#FF9F43', fontWeight:'900'}}>{themeConfig.app.appName}</h2>*/}
            {/*  <h4 className={"m-0 p-0"} style={{ color: 'rgba(47,34,27,0.94)', fontWeight:'600'}}>Restaurant</h4>*/}
            {/*</Col>*/}
          </Row>
        </Col>

        <Col md={6}
             className="navbar-nav d-none_ d-lg-block_ navbar_main d-flex justify-content-center align-items-center ">

          <div className={"top-navigate-btns"}>
            {!userStatus ? (
              <>
                <Link
                  to={"/home"}
                  // className={`top-wrapper ${windowPath === '/home' ? "top-wrapper-active" : ""}`}
                  onClick={() => setWindowPathHandler('/home')}
                >
                  <div className="nav_itm">
                    <Home />
                    <p>Home</p>
                  </div>
                </Link>

                <Link
                  to={"/services"}
                  // className={`top-wrapper ${windowPath === '/services' ? "top-wrapper-active" : ""}`}
                  onClick={() => setWindowPathHandler('/services')}
                >
                  <div className="nav_itm">
                    <FileText />
                    <p>Services</p>
                  </div>
                </Link>

                <Link
                  to={"/menus"}
                  // className={`top-wrapper ${windowPath === '/menus' ? "top-wrapper-active" : ""}`}
                  onClick={() => setWindowPathHandler('/menus')}
                >
                  <div className="nav_itm">
                    <Grid />
                    <p>Our Menus</p>
                  </div>
                </Link>
              </>
            ):(
              <Link
                to={"/shop"}
                // className={`top-wrapper ${windowPath === "/shop" ? "top-wrapper-active" : ""}`}
                onClick={() => setWindowPathHandler("/shop")}
              >
                <div className={"nav_itm"}>
                  <Grid />
                  <p>Explore Meals</p>
                </div>
              </Link>
            )}

            <Link
              to={userStatus === "CUSTOMER" ? '/reservation-form' : '/login'}
              // className={`top-wrapper ${windowPath === (userStatus === "CUSTOMER" ? "/reservation-form" : "/login") ? "top-wrapper-active" : ""}`}
              onClick={handleAddNewPlaceClick}
            >
              <div className={"nav_itm"}>
                <PlusCircle />
                <p>Make Reservation</p>
              </div>
            </Link>
          </div>
        </Col>

        <Col md={3} className={"d-flex justify-content-end align-items-center"}>
          {userStatus && (
            <NavbarUser {...props} />
          )}

        </Col>
      </Row>

    </Fragment>
  )
}

export default ThemeNavbar
