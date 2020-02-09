import React from 'react'
import { Switch, Route } from 'react-router-dom'
import { withRouter } from "react-router-dom"

import Header from './components/Header'
import LeftNavView from './views/LeftNavView'
import BlankView from './views/BlankView'


import './css/vendor.css'

function App() {
  return (
    <div>
      
      <Header title='ReactStarter'/>
      
      <div className="container-fluid">
        <Switch>
          <Route exact path='/' 
            render={props => 
              <LeftNavView />
            }
          />
          <Route path='/blog'
            render={props =>
              <BlankView title='Blog' />
            }
          />

        </Switch>        
      </div>


    </div>
  )
}

export default withRouter(App)
