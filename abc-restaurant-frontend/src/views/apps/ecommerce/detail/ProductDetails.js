// ** React Imports
import { Link } from 'react-router-dom'

// ** Third Party Components
import classnames from 'classnames'
import { Star, ShoppingCart, DollarSign, Heart, Share2, Facebook, Twitter, Youtube, Instagram } from 'react-feather'

// ** Reactstrap Imports
import {
  Row,
  Col,
  Button,
  CardText,
  DropdownItem,
  DropdownMenu,
  DropdownToggle,
  UncontrolledButtonDropdown
} from 'reactstrap'
import { MEALS_CHECKOUT } from "@src/router/routes/route-constant";

const Product = props => {
  // ** Props
  const { data, deleteWishlistItem, dispatch, addToWishlist, getProduct, productId, addToCart } = props

  // ** State
  // const [selectedColor, setSelectedColor] = useState('primary')

  // ** Renders color options
/*  const renderColorOptions = () => {
    return data.colorOptions.map((color, index) => {
      const isLastColor = data.colorOptions.length - 1 === index

      return (
        <li
          key={color}
          className={classnames('d-inline-block', {
            'me-25': !isLastColor,
            selected: selectedColor === color
          })}
          onClick={() => setSelectedColor(color)}
        >
          <div className={`color-option b-${color}`}>
            <div className={`filloption bg-${color}`}></div>
          </div>
        </li>
      )
    })
  }*/

  // ** Handle Wishlist item toggle
  const handleWishlist = val => {
    if (val) {
      dispatch(deleteWishlistItem(productId))
    } else {
      dispatch(addToWishlist(productId))
    }
    dispatch(getProduct(productId))
  }

  // ** Handle Move/Add to cart
  const handleCartBtn = (id, val) => {
    if (val === false) {
      dispatch(addToCart(id))
    }
    dispatch(getProduct(productId))
  }

  // ** Condition btn tag
  const CartBtnTag = data.isInCart ? Link : 'button'

  // Function to format meal type
  const formatMealType = (mealType) => {
    // Replace underscores with spaces and capitalize each word
    return mealType
      .replace(/_/g, ' ')
      .toLowerCase()
      .replace(/\b\w/g, char => char.toUpperCase())
  }


  return (
    <Row className='my-2'>
      <Col className='d-flex align-items-center justify-content-center mb-2 mb-md-0' md='5' xs='12'>
        <div className='d-flex align-items-center justify-content-center'>
          <img className='img-fluid product-img' src={data.image} alt={data.name} />
        </div>
      </Col>
      <Col md='7' xs='12'>
        <h4>{data.name}</h4>
        <CardText tag='span' className='item-company'>
          <a className='company-name' href='/' onClick={e => e.preventDefault()}>
            {data.brand}
          </a>
        </CardText>
        <div className='ecommerce-details-price d-flex flex-wrap mt-1'>
          <h4 className='item-price me-1'>Rs. {data.price}</h4>
          <ul className='unstyled-list list-inline'>
            {new Array(5).fill().map((listItem, index) => {
              return (
                <li key={index} className='ratings-list-item me-25'>
                  <Star
                    className={classnames({
                      'filled-star': index + 1 <= data.rating,
                      'unfilled-star': index + 1 > data.rating
                    })}
                  />
                </li>
              )
            })}
          </ul>
        </div>
        <CardText>
          Available -<span className='text-success ms-25'>Order Now</span>
        </CardText>
        <CardText>{data.description}</CardText>
        <ul className='product-features list-unstyled'>
          {data.hasFreeShipping ? (
            <li>
              <ShoppingCart size={19} />
              <span>Free Shipping</span>
            </li>
          ) : null}
          <li>
            {/*<DollarSign size={19} />*/}
            <span>Discount available : <span className={'fw-bold text-danger'}>Rs.{data.discount}</span></span>
          </li>
        </ul>
        <hr />
        <div className='product-color-options'>
          <h6>Cuisine Type -  {formatMealType(data.mealType)}</h6>
        </div>
        <hr />
        <div className='product-color-options'>
          <h6>Dish Types -  {formatMealType(data.subCategory)}</h6>
        </div>
        <hr />
        <div className='d-flex flex-column flex-sm-row pt-1'>
          <Button
            tag={CartBtnTag}
            className='btn-cart me-0 me-sm-1 mb-1 mb-sm-0'
            color='primary'
            onClick={() => handleCartBtn(data.id, data.isInCart)}
            /*eslint-disable */
            {...(data.isInCart
              ? {
                to: `${MEALS_CHECKOUT}`
              }
              : {})}
            /*eslint-enable */
          >
            <ShoppingCart className='me-50' size={14} />
            {data.isInCart ? 'View in cart' : 'Move to cart'}
          </Button>
          <Button
            className='btn-wishlist me-0 me-sm-1 mb-1 mb-sm-0 d-none'
            color='secondary'
            outline
            onClick={() => handleWishlist(data.isInWishlist)}
          >
            <Heart
              size={14}
              className={classnames('me-50', {
                'text-danger': data.isInWishlist
              })}
            />
            <span>Wishlist</span>
          </Button>
          <UncontrolledButtonDropdown className='dropdown-icon-wrapper btn-share'>
            <DropdownToggle className='btn-icon hide-arrow' color='secondary' caret outline>
              <Share2 size={14} />
            </DropdownToggle>
            <DropdownMenu end>
              <DropdownItem tag='a' href='/' onClick={e => e.preventDefault()}>
                <Facebook size={14} />
              </DropdownItem>
              <DropdownItem tag='a' href='/' onClick={e => e.preventDefault()}>
                <Twitter size={14} />
              </DropdownItem>
              <DropdownItem tag='a' href='/' onClick={e => e.preventDefault()}>
                <Youtube size={14} />
              </DropdownItem>
              <DropdownItem tag='a' href='/' onClick={e => e.preventDefault()}>
                <Instagram size={14} />
              </DropdownItem>
            </DropdownMenu>
          </UncontrolledButtonDropdown>
        </div>
      </Col>
    </Row>
  )
}

export default Product
