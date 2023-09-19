import React from 'react'
import PropTypes from 'prop-types'

import { Drawer } from '@mui/material'

const SideNav = props => {

  const content = "Menu"
  return (
    <Drawer
      anchor="left"
      onClose={onClose}
      open={open}
      PaperProps={{
        sx: {
          backgroundColor: 'neutral.800',
          color: 'common.white',
          width: 280
        }
      }}
      sx={{ zIndex: (theme) => theme.zIndex.appBar + 100 }}
      variant="temporary"
    >
      {content}
    </Drawer>
  )
}

SideNav.propTypes = {}

export default SideNav