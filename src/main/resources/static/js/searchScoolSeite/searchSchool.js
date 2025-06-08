
let countClick = 0;
$(document).ready(function () {



	$("#searchButton").click(function (event) {
		let searchText = $("#searchSchool").val();
		countClick = countClick + 1;

		$(".search_school").css({
			display: "block"
		});


		if (countClick < 1) {

			if ((searchText == " ") || (arrayID == 0)) {

				event.preventDefault();
				$(".json__object").remove();

				$(".search-wrapper").addClass("error");
				$(".noFound").html("<span>Your school not found...</span>");
				console.log("Your school not found...");
				console.log("countClick: " + countClick)

			}
		} else {


			$(".noFound span").remove();
			$(".search-wrapper").removeClass("error");

			countClick = 0;

		}




	});











	//-----------------------------------------------------------------------------------------------------------------------
	// Region selector
	const regions = [
		"Baden-Württemberg",
		"Bayern",
		"Berlin",
		"Brandenburg",
		"Bremen",
		"Hamburg",
		"Hessen",
		"Mecklenburg-Vorpommern",
		"Niedersachsen",
		"Nordrhein-Westfalen",
		"Rheinland-Pfalz",
		"Saarland",
		"Sachsen",
		"Sachsen-Anhalt",
		"Schleswig-Holstein",
		"Thueringen"
	];



	//Selected region
	let selectedRegion;
	// if (history.state == null) {
	// 	$('.selected-region').addClass('placeholder');
	// } else {
	// 	$('.selected-region').text(history.state).removeClass('placeholder');
	// }


	// Add region in the list
	function renderRegions(regionList) {
		$('.regions-list').empty();

		if (regionList.length === 0) {
			$('.no-results').show();
			return;
		}

		$('.no-results').hide();

		regionList.forEach(region => {
			const isSelected = region === selectedRegion;
			const regionItem = $(`
					<div class="region-item ${isSelected ? 'selected' : ''}" data-region="${region}">
						 ${region}
					</div>
			  `);

			$('.regions-list').append(regionItem);
		});
	}

	// First render of regions
	renderRegions(regions);

	// Open/close dropdown panel
	$('#region-selector-toggle').on('click', function (event) {
		event.stopPropagation(); // Prevent click from propagating to document
		$('.dropdown-panel').slideToggle(200);
		$('.dropdown-icon').toggleClass('open');
	});

	// Close dropdown when clicking outside
	// $(document).on('click', function (event) {
	// 	if (!$(event.target).closest('.region-selector').length) {
	// 		$('.dropdown-panel').slideUp(200);
	// 		$('.dropdown-icon').removeClass('open');
	// 	}
	// });

	// Search region
	$('.search-input').on('input', function () {
		const searchTerm = $(this).val().toLowerCase();

		if (searchTerm === '') {
			renderRegions(regions);
			return;
		}

		const filteredRegions = regions.filter(region =>
			region.toLowerCase().includes(searchTerm)
		);

		renderRegions(filteredRegions);
	});

	// Select region from the list
	$(document).on('click', '.region-item', function (e) {
		e.stopPropagation(); // Prevent click from propagating to document
		const region = $(this).data('region');

		//Update selected region

		selectedRegion = region.toLowerCase();

		// Update the displayed region name
		$('.selected-region').text(region).removeClass('placeholder');

		// Close dropdown panel
		$('.dropdown-panel').slideUp(200);
		$('.dropdown-icon').removeClass('open');

		history.replaceState(region, null, null);

		// URLSearch = new URL(window.location.href);
		// URLSearch.searchParams.set('region', selectedRegion.toLowerCase());
		// window.location.href = URLSearch.href;




		let selectedRegionNameValue = selectedRegion.toLowerCase().replace(/-/g, "_").replace(/ü/g, "ue").replace(/ö/g, "oe").replace(/ä/g, "ae");
		$.ajax({
			url: 'http://localhost:8080/',
			type: 'GET',
			data: {
				region: selectedRegionNameValue
			}
		}).done(function (response) {
			console.log("selectedRegionName okey: ", selectedRegionNameValue);

		})
			.fail(function (error) {
				console.error(error);
			});




		renderRegions(regions);


	});

	//-----------------------------------------------------------------------------------------------------------------------

});




