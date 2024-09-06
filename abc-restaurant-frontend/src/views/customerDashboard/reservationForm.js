import React, { useEffect, useState } from "react";
import { Button, Card, CardBody, Col, Form, Input, Label, Modal, ModalBody, ModalHeader, Row } from "reactstrap";
import Select from "react-select";
import { getAllRestaurantsIds } from "@src/services/restaurants";
import { PlusCircle } from "react-feather";
import "@styles/react/libs/flatpickr/flatpickr.scss";
import moment from "moment/moment";
import Flatpickr from "react-flatpickr";
import { validateReservationDetails } from "@src/utility/validation";
import { saveReservation } from "@src/services/reservation";
import toast from "react-hot-toast";
import ReservationTable from "@src/views/customerDashboard/ReservationTable/ReservationTable";

const reservationTypeList = [
  { value: "FAMILY_DINING", label: "Family Dining" },
  { value: "STREET_DINING", label: "Street Dining" },
  { value: "PARTIES_AND_CELEBRATION", label: "Parties and celebration" },
  { value: "BYOB", label: "BYOB" }
]

function ReservationForm() {

  const [show, setShow] = useState(true)
  const [restaurantList, setRestaurantList] = useState([])
  const [restaurant, setRestaurant] = useState(null)
  const [reservationType, setReservationType] = useState(null)
  const [isEdit, setIsEdit] = useState(false)
  const [form, setForm] = useState({
    restaurantId: 0,
    name: JSON.parse(localStorage.getItem("USER_OBJECT")).name ?? "",
    email: JSON.parse(localStorage.getItem("USER_OBJECT")).email ?? "",
    phone: "",
    date: new Date(),
    reservationType: "",
    seats: 0,
    note: ""
  })

  useEffect(() => {
    fetchRestaurantList()
  }, []);

  const fetchRestaurantList = () => {
    getAllRestaurantsIds().then(async response => {

      const data = response.data.map((item, index) => ({
        ...item,
        uniqueKey: `${item.id}-${index}`
      }))
      await setRestaurantList(data)
    })
  }

  const handleRestaurantIdChange = (e) => {
    setRestaurant(e);
    setForm((prev) => ({
      ...prev,
      restaurantId: e.value
    }))
  }

  const onTextChange = (event) => {
    const { name, value } = event.target
    setForm((prev) => ({ ...prev, [name]: value }))
  }

  const handleDateChange = (date) => {
    setForm(prev => ({
      ...prev,
      date: date
    }))
  }

  const handleReservationTypeChange = (e) => {
    setReservationType(e)
    setForm((prev) => ({
      ...prev,
      reservationType: e.value
    }))
  }

  const clearForm = () => {
    setShow(!show)
    setRestaurant(null)
    setReservationType(null)
    setForm({
      restaurantId: 0,
      name: JSON.parse(localStorage.getItem("USER_OBJECT")).name ?? "",
      email: JSON.parse(localStorage.getItem("USER_OBJECT")).email ?? "",
      phone: "",
      date: new Date(),
      reservationType: "",
      seats: 0,
      note: ""
    })
  }

  const createNewReservation = (form) => {
    return {
      restaurantId: form.restaurantId ?? 0,
      name: form.name ?? null,
      email: form.email ?? null,
      phone: form.phone ?? null,
      date: form.date ? new Date(form.date).toISOString() : null,
      reservationType: form.reservationType ?? null,
      seats: parseInt(form.seats) ?? 0,
      note: form.note ?? null
    }
  }

  const apiHandler = () => {
    if (validateReservationDetails(form)){
      saveReservation(createNewReservation(form))
        .then(response => {
            if (response.success) {
              toast.success("reservation saved successfully")
              clearForm()
              // setIsRefresh(true)
            } else {
              toast.error(response.message)
            }
        })
            .catch((error) => {
              console.error("API Request Error:", error.message)
            })
    }
  }

  const reservationFormModal = (
    <Modal isOpen={show}
           toggle={() => {
             setShow(!show)
             clearForm()
           }}
           className="modal-dialog-centered modal-md">
      <ModalHeader toggle={() => {
        clearForm()
        setShow(!show)
      }}>{isEdit ? 'Update Reservation' : 'Make Reservation'}</ModalHeader>
      <ModalBody>
        <Card>
          <CardBody className="py-2 my-25">
            <Form className="mt-2 pt-50">
              <Row>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="restaurant">
                    Restaurant
                  </Label>
                  <Select
                    id="restaurant"
                    value={restaurant}
                    className="react-select"
                    classNamePrefix="select"
                    options={restaurantList}
                    isClearable={false}
                    onChange={handleRestaurantIdChange}
                  />
                </Col>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="name">
                    Name
                  </Label>
                  <Input
                    id='name'
                    type={'text'}
                    name={'name'}
                    value={form.name}
                    placeholder='John perera'
                    disabled={JSON.parse(localStorage.getItem("USER_OBJECT")).name !== undefined}
                    onChange={onTextChange}
                  />
                </Col>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="email">
                    E-mail
                  </Label>
                  <Input
                    id="email"
                    type="email"
                    name="email"
                    disabled={JSON.parse(localStorage.getItem("USER_OBJECT")).email !== undefined}
                    value={form.email}
                    placeholder="Email"
                    onChange={onTextChange}
                  />
                </Col>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="phoneNumber">
                    Phone Number
                  </Label>
                  <Input
                    id="phoneNumber"
                    type={"number"}
                    name="phone"
                    value={form.phone}
                    placeholder="077xxxxxxx"
                    onChange={onTextChange}
                  />
                </Col>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="date">
                    Date
                  </Label>
                  <Flatpickr
                    className='form-control'
                    value={form.date ? new Date(form.date) : null}
                    onChange={date => handleDateChange(date[0])}
                    options={{ minDate: new Date() }}
                    placeholder={moment(new Date()).format("YYYY-MM-DD")}
                    id='default-picker'
                  />
                </Col>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="reservationType">
                    Reservation Type
                  </Label>
                  <Select
                    id="reservationType"
                    value={reservationType}
                    className="react-select"
                    classNamePrefix="select"
                    options={reservationTypeList}
                    isClearable={false}
                    onChange={handleReservationTypeChange}
                  />
                </Col>
                <Col sm="6" className="mb-1">
                  <Label className="form-label" for="homeAddress">
                    Seats
                  </Label>
                  <Input
                    id="homeAddress"
                    type={"number"}
                    name="seats"
                    value={form.seats}
                    placeholder="xxx"
                    onChange={onTextChange}
                  />
                </Col>
                <Col sm="12" className="mb-1">
                  <Label className="form-label" for="status">
                    Special Note
                  </Label>
                  <Input
                    id="homeAddress"
                    type={"textarea"}
                    name="note"
                    value={form.note}
                    placeholder="text here..."
                    onChange={onTextChange}
                  />
                </Col>
                <Col className="mt-2" sm="12">
                  <Button type="button"
                          className="me-1"
                          color="primary"
                          onClick={apiHandler}
                  >
                    Save Changes
                  </Button>
                  <Button color="secondary"
                          outline
                          type={"button"}
                          onClick={clearForm}
                  >
                    Cancel
                  </Button>
                </Col>
              </Row>
            </Form>
          </CardBody>
        </Card>
      </ModalBody>
    </Modal>
  )

  const handleAddReservationClick = () => {
    setIsEdit(false)
    setShow(true)
    clearForm()
  }


  return (
    <>
      <div>
        <Row className="d-flex ">
          <Col md={6}>
            <h1>Reservations</h1>
          </Col>
          <Col md={6} className={'d-flex justify-content-end align-items-center'}>
            <Button className='width-36-per mt-1 me-2'
                    color={'warning'}
                    onClick={handleAddReservationClick}
            >

              <PlusCircle size={18} /> <span> Add New Reservation</span>
            </Button>
          </Col>
        </Row>

        <Row className={'mt-3'}>
          <ReservationTable /> {/* Pass handleEditClick */}
          {reservationFormModal}
        </Row>
      </div>
    </>
  )
}

export default ReservationForm