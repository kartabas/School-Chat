import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

function BoxAnimation() {
	const [pos, setPos] = React.useState(0);

	React.useEffect(() => {
		const interval = setInterval(() => {
			setPos(p => (p + 3) % 300);
		}, 20);
		return () => clearInterval(interval);
	}, []);

	return (
		<div style={{ padding: 30 }}>
			<h1>💫 React Animation</h1>
			<div style={{
				width: 50, height: 50, backgroundColor: 'tomato',
				transform: `translateX(${pos}px)`,
				transition: 'transform 0.02s linear'
			}} />
		</div>
	);
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<BoxAnimation />);
