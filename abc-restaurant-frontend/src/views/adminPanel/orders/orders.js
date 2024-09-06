import React, { useState, useEffect } from "react"
import ReactPaginate from "react-paginate"
import DataTable from "react-data-table-component"
import {
  Card,
  CardHeader,
  CardTitle,
  Input,
  Badge,
  Modal,
  ModalHeader,
  ModalBody,
  Button,
  Row,
  Col,
  Label
} from "reactstrap";
import moment from "moment"
import {
  getAllOrdersFromTableType,
  getOrderQueriesById,
  saveQueryForReservation
} from "@src/services/reservation"
import "./orders.scss"
import { Assets } from "@src/assets/images"
import { getBadgeColor, isUserLoggedIn } from "@utils"
import { USER_ROLES } from "@src/const/const";

const Orders = () => {
  const [currentPage, setCurrentPage] = useState(0)
  const [searchValue, setSearchValue] = useState("")
  const [filteredData, setFilteredData] = useState([])
  const [data, setData] = useState([])
  const [modal, setModal] = useState(false)
  const [selectedReservationId, setSelectedReservationId] = useState(null)
  const [queries, setQueries] = useState([])
  const [replyMessage, setReplyMessage] = useState("")
  const [userData, setUserData] = useState(null)

  useEffect(() => {
    if (isUserLoggedIn() !== null) {
      setUserData(JSON.parse(localStorage.getItem("USER_OBJECT")))
    }
  }, [])
  const columns = [
    {
      name: "Order ID",
      selector: row => row.reservation.id,
      sortable: true,
      minWidth: "50"
    },
    {
      name: "Table Type",
      selector: row => row.reservation.mealOrderType,
      sortable: true,
      minWidth: "160px"
    },
    {
      name: "Total Amount",
      selector: row => row.reservation.total,
      sortable: true,
      minWidth: "120px",
      cell: row => `$${row.reservation.total.toFixed(2)}`
    },
    {
      name: "Created Date",
      selector: row => row.reservation.createdDate,
      sortable: true,
      minWidth: "220px",
      cell: row => moment(row.reservation.createdDate).format("YYYY-MM-DD  h:mm a")
    },
    {
      name: "Operation Status",
      selector: row => row.reservation.operationalStatus,
      sortable: true,
      minWidth: "160px",
      cell: row => (
        <Badge color={getStatusBadgeColor(row.reservation.operationalStatus)} className="badge-glow">
          {row.reservation.operationalStatus}
        </Badge>
      )
    },
    {
      name: "Actions",
      allowOverflow: true,
      minWidth: "300px",
      cell: row => (
        <div>
          <Button color="primary" size="sm" onClick={() => handleRespond(row.reservation.id)}>Respond</Button>
          <Button color="success" size="sm" onClick={() => handleApprove(row.reservation.id)}
                  className="ms-2">Approve</Button>
        </div>
      )
    }
  ]

  const ExpandableTable = ({ data }) => (
    <div className="expandable-content p-2">
      <Row>
        <Col md={6}>
          <p><strong>Order ID: </strong>{data.reservation.orderId}</p>
          <p><strong>Created Date: </strong>{moment(data.reservation.createdDate).format("YYYY MMMM Do - h:mm a")}</p>
        </Col>
        <Col md={6}>
          <p><strong>Meal Order Type: </strong>{data.reservation.mealOrderType}</p>
          <p><strong>Total Amount: </strong>${data.reservation.total.toFixed(2)}</p>
        </Col>
      </Row>
      <Row>
        <Col>
          <p><strong>Items:</strong></p>
          {data.items && data.items.length ? (
            <ul>
              {data.items.map(item => (
                <li key={item.id}>
                  <img src={item.image} alt={item.name}
                       style={{ width: "50px", height: "50px", marginRight: "10px" }} />
                  {item.name} - RS. {item.price.toFixed(2)} x {item.qty}
                </li>
              ))}
            </ul>
          ) : (
            <p>No items found</p>
          )}
        </Col>
      </Row>
    </div>
  )

  useEffect(() => {
    fetchAllOrders()
  }, [])

  const fetchAllOrders = () => {
    getAllOrdersFromTableType().then(response => {
      const data = response.data.map((item, index) => ({
        ...item,
        uniqueKey: `${item.reservation.id}-${index}`
      }))
      setData(data)
      setFilteredData(data)
    })
  }

  const handleRespond = async id => {
    setSelectedReservationId(id)
    await fetchQueries(id)
    toggleModal()
  }

  const fetchQueries = async id => {
      await getOrderQueriesById(id)
        .then(response => {
          setQueries(response.data || [])
        }).catch(error => {
          console.error("Error fetching queries:", error)
          setQueries([])
        })
  }

  const handleApprove = id => {
    console.log("Approve button clicked for reservation ID:", id)
  }

  const handleReplySubmit = () => {
    if (!replyMessage.trim()) return

    const userDetails = {
      mealOrderId: selectedReservationId,
      userRole: userData.userRole,
      queryType: "MEAL",
      message: replyMessage,
      userId: userData.id
    }

    saveQueryForReservation(userDetails)
      .then(async response => {
        if (response.success) {
          await fetchQueries(selectedReservationId)
          setReplyMessage("")
        } else {
          console.error("Error submitting reply:", response.message)
        }
      })
      .catch(error => {
        console.error("Error submitting reply:", error)
      })
  }

  const toggleModal = () => {
    setModal(!modal)
  }

  const handlePagination = page => {
    setCurrentPage(page.selected)
  }

  const handleFilter = e => {
    const value = e.target.value.toLowerCase()
    setSearchValue(value)

    if (value.length) {
      const updatedData = data.filter(item => {
        const orderIdMatch = item.reservation.orderId && item.reservation.orderId.toLowerCase().includes(value)
        const statusMatch = item.reservation.status && item.reservation.status.toLowerCase().includes(value)
        const mealOrderTypeMatch = item.reservation.mealOrderType && item.reservation.mealOrderType.toLowerCase().includes(value)

        return orderIdMatch || statusMatch || mealOrderTypeMatch
      })
      setFilteredData(updatedData)
    } else {
      setFilteredData(data)
    }
  }

  const Previous = () => (
    <span className="align-middle d-none d-md-inline-block">Prev</span>
  )

  const Next = () => (
    <span className="align-middle d-none d-md-inline-block">Next</span>
  )

  const CustomPagination = () => (
    <ReactPaginate
      previousLabel={<Previous size={10} />}
      nextLabel={<Next size={10} />}
      forcePage={currentPage}
      onPageChange={handlePagination}
      pageCount={Math.ceil((searchValue.length ? filteredData.length : data.length) / 7)}
      breakLabel={"..."}
      pageRangeDisplayed={2}
      marginPagesDisplayed={2}
      activeClassName={"active"}
      pageClassName={"page-item"}
      nextLinkClassName={"page-link"}
      nextClassName={"page-item next"}
      previousClassName={"page-item prev"}
      previousLinkClassName={"page-link"}
      pageLinkClassName={"page-link"}
      breakClassName="page-item"
      breakLinkClassName="page-link"
      containerClassName={"pagination react-paginate pagination-sm justify-content-end pe-1 mt-1"}
    />
  )

  const queryModal = (<Modal scrollable isOpen={modal} toggle={toggleModal} size="md">
    <ModalHeader toggle={toggleModal}>Respond to Queries</ModalHeader>
  <ModalBody className="modal-body">
    <div className="chat-container">
      {queries.length !== 0 ? (
        queries.map(query => {
          let avatar, name

          if (query.userRole === "ADMIN" && query.admin) {
            avatar = query.admin.img || Assets.avater || "default-admin-img.png"
            name = query.admin.name || "Admin"
          } else if (query.userRole === USER_ROLES[1] && query.staff) {
            avatar = query.staff.img || Assets.avater || "default-staff-img.png"
            name = query.staff.name || "Staff"
          } else if (query.userRole === USER_ROLES[0] && query.user) {
            avatar = query.user.img || Assets.avater || "default-customer-img.png"
            name = query.user.name || "Customer"
          } else {
            avatar = "default-avatar.png"
            name = "Unknown"
          }

          return (
            <div key={query.id} className={`chat-message ${query.userRole.toLowerCase()}`} style={userData.userRole === query.userRole ? {justifyContent: "flex-end"} : {justifyContent: "flex-start"}}>
              <div className="message-info">
                <img src={avatar} alt={`${name} Avatar`} className="avatar" />
                <div className="message-details" style={{ minWidth: "180px" }}>
                  <div className="message-header">
                    <small color="info" className="message-role pe-2">
                      {query.userRole} | <small className="message-name text-end">{name.split(" ")[0]}</small>
                    </small>
                  </div>
                  <p className="message-text pb-1">{query.message}</p>
                  <small className="text-dark text-end">
                    {moment(query.createdDate).format("MMM D, YYYY h:mm A")}
                  </small>
                </div>
              </div>
            </div>
          )
        })
      ) : (
        <div className={'text-center'}>Not found chat yet!</div>
      )}
    </div>
    <div className="reply-section p-1">
      <Input
        type="textarea"
        value={replyMessage}
        onChange={e => setReplyMessage(e.target.value)}
        placeholder="Type your reply here..."
        className="reply-input"
      />
      <Button color="primary" onClick={handleReplySubmit} className="reply-button">Send</Button>
    </div>
  </ModalBody>
</Modal>)

  return (
    <div className="reservation-container">
      <Card>
        <CardHeader className='border-bottom'>
          <CardTitle tag='h4'>Manage Orders</CardTitle>
        </CardHeader>
        <Row className='justify-content-start mx-0'>
          <Col className='d-flex align-items-center justify-content-end mt-1' md='4' sm='12'>
            <Label className='me-1' for='search-input-1'>
              Search
            </Label>
            <Input
              className='dataTable-filter mb-50'
              type='text'
              bsSize='sm'
              id='search-input-1'
              value={searchValue}
              onChange={handleFilter}
            />
          </Col>
        </Row>
        <div className='react-dataTable'>
          <DataTable
            columns={columns}
            data={filteredData}
            expandableRows
            expandableRowsComponent={ExpandableTable}
            pagination
            paginationComponent={CustomPagination}
            keyField="uniqueKey"
            className="data-table"
          />
        </div>
      </Card>
      {queryModal}
    </div>
  )
}

export default Orders
