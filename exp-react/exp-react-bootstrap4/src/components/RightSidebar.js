import React from 'react';

export default function RightSidebar(props) {
    return (
        <div className="d-none d-xl-block col-xl-2 bd-toc">
            <ul className="section-nav">
                <li className="toc-entry toc-h2"><a href="#examples">Submenu Link</a></li>
            </ul>
        </div>
    );
}