// ** React Imports
import { Link, useNavigate } from "react-router-dom"

// ** Third Party Components
import classnames from 'classnames'
import { Star, ShoppingCart, Zap } from "react-feather"

// ** Reactstrap Imports
import { Card, CardBody, CardText, Button, Badge } from 'reactstrap'
import {
  MEALS_CHECKOUT,
  SHOP_PRODUCTS_DETAILS_PATH
} from "@src/router/routes/route-constant";

const ProductCards = props => {
  // ** Props
  const {
    store,
    products,
    dispatch,
    addToCart,
    activeView,
    getProducts,
    getCartItems

  } = props

  const navigate = useNavigate()

  // ** Handle Move/Add to cart
  const handleCartBtn = (id, val) => {
    if (val === false) {
      dispatch(addToCart(id))
    }
    dispatch(getCartItems())
    dispatch(getProducts(store.params))
  }

  const cardImgStyles = {
    width: "100%",
    height: "210px", // Adjust the height as needed
    objectFit: "cover" // Ensures the image covers the entire area without distorting
  }


  // ** Renders products
  const renderProducts = () => {
    if (products.length) {
      return products.map(item => {
        const CartBtnTag = item.isInCart ? Link : 'button'
        const handleButtonClick = () => {
          navigate(`${SHOP_PRODUCTS_DETAILS_PATH}/${item.id}`)
        }
        return (
          <Card className='ecommerce-card' key={item.name}>
            <div className='item-img text-center mx-auto'>
                <img className='img-fluid card-img-top'
                     src={item.image}
                     alt={item.name}
                     style={cardImgStyles}
                />
            </div>
            <CardBody>
              <div className='item-wrapper'>
                <div className='item-rating'>
                  <ul className='unstyled-list list-inline'>
                    {new Array(5).fill().map((listItem, index) => {
                      return (
                        <li key={index} className='ratings-list-item me-25'>
                          <Star
                            className={classnames({
                              'filled-star': index + 1 <= item.rating,
                              'unfilled-star': index + 1 > item.rating
                            })}
                          />
                        </li>
                      )
                    })}
                  </ul>
                </div>
                <div className='item-cost'>
                  <h6 className='item-price'>RS. {item.price}</h6>
                </div>
              </div>
              <h6 className='item-name'>
                <Link className='text-body' to={`${SHOP_PRODUCTS_DETAILS_PATH}/${item.id}`}>
                  {item.name}
                </Link>
                <CardText tag='span' className='item-company'>
                  By{' '}
                  <a className='company-name' href='/' onClick={e => e.preventDefault()}>
                    {item.brand}
                  </a>
                </CardText>
              </h6>
              <CardText className='item-description'>{item.description}</CardText>
            </CardBody>
            <div className='item-options text-center'>
              <div className='item-wrapper'>
                <div className='item-cost'>
                  <h4 className='item-price'>${item.price}</h4>
                  {item.hasFreeShipping ? (
                    <CardText className='shipping'>
                      <Badge color='light-success'>Free Shipping</Badge>
                    </CardText>
                  ) : null}
                </div>
              </div>

              <Button
                className='btn-wishlist'
                color='light'
                onClick={(handleButtonClick)}
              >
                <Zap size={14} />
                <span>See More Details</span>
              </Button>

              <Button
                color='warning'
                tag={CartBtnTag}
                className='btn-cart move-cart'
                onClick={() => handleCartBtn(item.id, item.isInCart)}
                /*eslint-disable */
                {...(item.isInCart
                  ? {
                      to: `${MEALS_CHECKOUT}`
                    }
                  : {})}
                /*eslint-enable */
              >
                <ShoppingCart className='me-50' size={14} />
                <span>{item.isInCart ? 'View In Cart' : 'Add To Cart'}</span>
              </Button>
            </div>
          </Card>
        )
      })
    }
  }

  return (
    <div
      className={classnames({
        'grid-view': activeView === 'grid',
        'list-view': activeView === 'list'
      })}
    >
      {renderProducts()}
    </div>
  )
}

export default ProductCards
