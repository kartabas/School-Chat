


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





	$('.json__object').click(function () {
		let nameValue = $(this).find('.name').text();
		$("#searchSchool").val(nameValue);

	});

	$(".json__object").on("click", function () {
		let index = $(".json__object").index(this);


		let currentSchool = schools[index];

		console.log(currentSchool);
		//$('#currentSchool').val(currentSchool);
		$('#currentSchool').val(JSON.stringify(currentSchool));

		$('#schoolForm').submit();
	});




	//-----------------------------------------------------------------------------------------------------------------------
	// Region selector
	const regions = [
		"Alle Bundesländer",
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
		"Thüringen"
	];


	//Selected region
	let selectedRegion = null;

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
	$('#region-selector-toggle').on('click', function () {
		$('.dropdown-panel').slideToggle(200);
		$('.dropdown-icon').toggleClass('open');
	});

	// Close dropdown when clicking outside
	$(document).on('click', function (event) {
		if (!$(event.target).closest('.region-selector').length) {
			$('.dropdown-panel').slideUp(200);
			$('.dropdown-icon').removeClass('open');
		}
	});

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
	$(document).on('click', '.region-item', function () {
		const region = $(this).data('region');

		//Update selected region
		selectedRegion = region;

		// Update the displayed region name
		$('.selected-region').text(region).removeClass('placeholder');

		// Close dropdown panel
		$('.dropdown-panel').slideUp(200);
		$('.dropdown-icon').removeClass('open');


		console.log("Вибраний регіон:", selectedRegion);


		renderRegions(regions);
	});

	//-----------------------------------------------------------------------------------------------------------------------


});




