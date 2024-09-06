// ** Icon Imports
import { PlusCircle } from 'react-feather'

// ** Reactstrap Imports
import { Row, Col, Form, Label, Input, Button, Card, CardHeader, CardTitle, CardBody, CardText } from 'reactstrap'

const Payment = () => {
  return (
    <Form
      className='list-view product-checkout'
      onSubmit={e => {
        e.preventDefault()
      }}
    >
      <div className='payment-type'>
        <Card>
          <CardHeader className='flex-column align-items-start'>
            <CardTitle tag='h4'>Payment options</CardTitle>
            <CardText className='text-muted mt-25'>Be sure to click on correct payment option</CardText>
          </CardHeader>
          <CardBody>
            <ul className='other-payment-options list-unstyled'>
              <li className='py-50'>
                <div className='form-check'>
                  <Input type='radio' name='paymentMethod' id='credit-card' />
                  <Label className='form-label' for='credit-card'>
                    Credit / Debit / ATM Card
                  </Label>
                </div>
              </li>
              <li className='py-50'>
                <div className='form-check'>
                  <Input type='radio' name='paymentMethod' id='payment-cod' />
                  <Label className='form-label' for='payment-cod'>
                    Cash On Delivery
                  </Label>
                </div>
              </li>
            </ul>
          </CardBody>
        </Card>
      </div>
      <div className='amount-payable checkout-options'>
        <Card>
          <CardHeader>
            <CardTitle tag='h4'>Price Details</CardTitle>
          </CardHeader>
          <CardBody>
            <ul className='list-unstyled price-details'>
              <li className='price-detail'>
                <div className='details-title'>Price of 3 items</div>
                <div className='detail-amt'>
                  <strong>Rs. 699.30</strong>
                </div>
              </li>
              <li className='price-detail'>
                <div className='details-title'>Delivery Charges</div>
                <div className='detail-amt discount-amt text-success'>Free</div>
              </li>
            </ul>
            <hr />
            <ul className='list-unstyled price-details'>
              <li className='price-detail'>
                <div className='details-title'>Amount Payable</div>
                <div className='detail-amt fw-bolder'>Rs. 699.30</div>
              </li>
            </ul>
          </CardBody>
        </Card>
      </div>
    </Form>
  )
}

export default Payment
