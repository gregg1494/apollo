import React from 'react';
import { Player } from 'video-react';

export default vid => {
    return (
        <Player
            playsInline
            poster="/assets/poster.png"
            src="https://media.w3.org/2010/05/sintel/trailer_hd.mp4"
        />
    );
};
