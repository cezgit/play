import * as React from 'react'

import Filters from '../components/Filters'
import LeftSidebar from '../components/LeftSidebar'
import RightSidebar from '../components/RightSidebar'
import Main from '../components/Main'

export default function LeftNavView(props) {
  return (
    <div className="row flex-xl-nowrap">
          <div className="col-12 col-md-3 col-xl-2 bd-sidebar">
              <Filters />
              <LeftSidebar />
          </div>

          <RightSidebar />

          <Main />
      
    </div>
  )
}