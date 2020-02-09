import React from 'react'
import { NavLink } from 'react-router-dom'

export default function LeftSidebar(props) {
    return (
        <nav className="collapse bd-links" id="bd-docs-nav">
            <div className="bd-toc-item active">
                <NavLink className="bd-toc-link" to="/">Home</NavLink>                
            </div>
            <div className="bd-toc-item">
                <NavLink className="bd-toc-link" to="/blog">Blog</NavLink>                
            </div>
        </nav>
    )
}
