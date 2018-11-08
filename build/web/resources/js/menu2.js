/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



(function() {

	var bodyEl = document.body,
		content = document.querySelector( '.content-wrap' ),
		openbtn = document.getElementById( 'open-button2' ),
		closebtn = document.getElementById( 'close-button2' ),
		isOpen = false;

//		morphEl = document.getElementById( 'morph-shape' ),
//		s = Snap( morphEl.querySelector( 'svg' ) );
//		path = s.select( 'path' );
//		initialPath = this.path.attr('d'),
//		pathOpen = morphEl.getAttribute( 'data-morph-open' ),
//		isAnimating = false;

	function init() {
		initEvents();
	}

	function initEvents() {
		openbtn.addEventListener( 'click', toggleMenu );
		if( closebtn ) {
			closebtn.addEventListener( 'click', toggleMenu );
		}

		// close the menu element if the target it´s not the menu element or one of its descendants..
		content.addEventListener( 'click', function(ev) {
			var target = ev.target;
			if( isOpen && target !== openbtn ) {
				toggleMenu();
			}
		} );
	}

	function toggleMenu() {
//		if( isAnimating ) return false;
//		isAnimating = true;
		var openbtn = document.getElementById( 'open-button2' );
		if( isOpen ) {
			openbtn.style.display = 'block';
			classie.remove( bodyEl, 'show-menu2' );
			// animate path
			setTimeout( function() {
				// reset path
				//path.attr( 'd', initialPath );
				//isAnimating = false; 
			}, 300 );
		}
		else {
			openbtn.style.display = 'none';
			classie.add( bodyEl, 'show-menu2' );
			// animate path
			//path.animate( { 'path' : pathOpen }, 400, mina.easeinout, function() { isAnimating = false; } );
		}
		isOpen = !isOpen;
	}

	init();

})();