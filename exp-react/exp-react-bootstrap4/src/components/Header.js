import React from 'react'
import Social from './Social'

import { NavLink } from 'react-router-dom'

export default function Header(props) {

    return (
        <header className="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar">            
            <NavLink className="navbar-brand mr-0 mr-md-2" ariaLabel="Bootstra" to="/">{props.title}</NavLink>
            <div className="navbar-nav-scroll">
                <ul className="navbar-nav bd-navbar-nav flex-row">
                    <li className="nav-item">                        
                        <NavLink className="nav-link" to="/">Home</NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink className="nav-link" to="/blog">Blog</NavLink>
                    </li>
                </ul>
            </div>

            <Social/>
        </header>        
    )


}