/*<![CDATA[*/


// State Management
let countClick = 0;
let arrayID = /*[[${schools != null ? schools.size() : 0}]]*/ 0;
let selectedRegion = null;
let recentSearches = JSON.parse(localStorage.getItem('recentSearches') || '[]');

// Check if we have schools on page load
if (arrayID > 0) {
	$('.search_school').css('display', 'block');
	updateStats(arrayID);
} else if (arrayID === 0 && $('#searchSchool').val()) {
	$('.search-wrapper').addClass('error');
	$('.noFound').html("<span>😕 Your school not found...</span>");
	$('.search_school').css('display', 'block');
}

// German regions
const regions = [
	"Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen",
	"Hamburg", "Hessen", "Mecklenburg-Vorpommern", "Niedersachsen",
	"Nordrhein-Westfalen", "Rheinland-Pfalz", "Saarland", "Sachsen",
	"Sachsen-Anhalt", "Schleswig-Holstein", "Thueringen"
];

// School Class
class School {
	constructor({ officialId, id, name, schoolType, address, fullTimeSchool, state, phone, fax, latitude, longitude }) {
		this.official_id = officialId;
		this.id = id;
		this.name = name;
		this.schoolType = schoolType;
		this.address = address;
		this.fullTimeSchool = fullTimeSchool;
		this.state = state;
		this.phone = phone;
		this.fax = fax;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}

// Utility Functions
function normalizeRegionName(region) {
	return region.toLowerCase()
		.replace(/-/g, "_")
		.replace(/ü/g, "ue")
		.replace(/ö/g, "oe")
		.replace(/ä/g, "ae");
}

function addRecentSearch(term) {
	if (!term || term.trim() === '') return;
	recentSearches = [term, ...recentSearches.filter(s => s !== term)].slice(0, 5);
	localStorage.setItem('recentSearches', JSON.stringify(recentSearches));
	renderRecentSearches();
}

function renderRecentSearches() {
	const container = $('#recent-searches');
	const items = $('#recent-items');

	if (recentSearches.length === 0) {
		container.hide();
		return;
	}

	container.show();
	items.html(recentSearches.map(term =>
		`<span class="recent-item">
                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <circle cx="12" cy="12" r="10"></circle>
                        <polyline points="12 6 12 12 16 14"></polyline>
                    </svg>
                    ${term}
                </span>`
	).join(''));

	$('.recent-item').on('click', function () {
		const term = $(this).text().trim();
		$('#searchSchool').val(term);
		$('#searchForm').submit();
	});
}

function updateStats(resultsCount) {
	$('#stats-bar').show();
	$('#current-region').text(selectedRegion || 'All Regions');
	$('#results-count').text(resultsCount);
}

// Region Selector Functions
function renderRegions(regionList) {
	const $regionsList = $('.regions-list');
	$regionsList.empty();

	if (regionList.length === 0) {
		$('.no-results').show();
		return;
	}

	$('.no-results').hide();

	regionList.forEach(region => {
		const isSelected = region === selectedRegion;
		const regionItem = $(`
                    <div class="region-item ${isSelected ? 'selected' : ''}" data-region="${region}">
                        ${isSelected ? '✓ ' : ''}${region}
                    </div>
                `);
		$regionsList.append(regionItem);
	});
}

// Initialize
$(document).ready(function () {
	// Load saved region from history
	if (history.state) {
		selectedRegion = history.state;
		$('.selected-region').text(selectedRegion).removeClass('placeholder');
	}

	renderRegions(regions);
	renderRecentSearches();

	// Region selector toggle
	$('#region-selector-toggle').on('click', function (e) {
		e.stopPropagation();
		$('.dropdown-panel').slideToggle(200);
		$('.dropdown-icon').toggleClass('open');
		if ($('.dropdown-panel').is(':visible')) {
			$('.search-input').focus();
		}
	});

	// Close dropdown when clicking outside
	$(document).on('click', function (e) {
		if (!$(e.target).closest('.region-selector').length) {
			$('.dropdown-panel').slideUp(200);
			$('.dropdown-icon').removeClass('open');
		}
	});

	// Search regions
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

	// Select region
	$(document).on('click', '.region-item', function (e) {
		e.stopPropagation();
		const region = $(this).data('region');
		selectedRegion = region;

		$('.selected-region').text(region).removeClass('placeholder');
		$('.dropdown-panel').slideUp(200);
		$('.dropdown-icon').removeClass('open');

		history.replaceState(region, null, null);

		// Send region to backend
		const normalizedRegion = normalizeRegionName(region);
		$.ajax({
			url: window.location.origin + "/",
			type: 'GET',
			data: { region: normalizedRegion }
		}).done(function () {
			console.log("Region selected:", normalizedRegion);
		}).fail(function (error) {
			console.error("Region selection error:", error);
		});

		renderRegions(regions);
	});

	// Clear recent searches
	$('#clear-recent').on('click', function () {
		recentSearches = [];
		localStorage.removeItem('recentSearches');
		renderRecentSearches();
	});

	// Search form submission
	$('#searchForm').on('submit', function (e) {
		e.preventDefault();

		const searchText = $('#searchSchool').val().trim();
		countClick++;

		$('.search_school').css('display', 'block');

		if (countClick < 1) {
			if (!searchText || arrayID == 0) {
				$('.json__object').remove();
				$('.search-wrapper').addClass('error');
				$('.noFound').html("<span>😕 Your school not found...</span>");
				console.log("Your school not found...");
				console.log("countClick: " + countClick);
				return;
			}
		} else {
			$('.noFound span').remove();
			$('.search-wrapper').removeClass('error');
			countClick = 0;
		}

		if (!searchText) {
			$('.search-wrapper').addClass('error');
			$('.noFound').html("<span>⚠️ Please enter a search term</span>");
			return;
		}

		// Show loading
		$('#loading').show();
		$('.search_school').hide();

		// Submit with AJAX
		let searchSchoolRequest = $(this).serialize();
		$.ajax({
			url: window.location.origin + "/",
			type: 'POST',
			data: searchSchoolRequest,
			success: function (response) {
				$('#loading').hide();

				let parsedResponse = $('<div>').html(response);
				let schools = parsedResponse.find('.json__object').map(function () {
					return {
						schoolInfo: $(this).find('.schoolInfo').val()
							.replace(/(\w+)=/g, '"$1":')
							.replace(/'/g, '"')
					};
				}).get();

				let schoolsLength = schools.length;

				if (schoolsLength > 0) {
					arrayID = 1;

					let searchResult = parsedResponse.find('#searchResult');
					$('#searchResult').empty();
					$('#searchResult').html(searchResult.html());

					$('.search_school').css('display', 'block');
					addRecentSearch(searchText);
					updateStats(schoolsLength);

					// Add click handlers
					setupSchoolClickHandlers(schools);

				} else {
					arrayID = 0;
					$('.search-wrapper').addClass('error');
					$('#searchResult').find('.noFound').html("<span>😕 No schools found. Try a different search term.</span>");
					$('.search_school').css('display', 'block');
					updateStats(0);
				}

				countClick = 0;
			},
			error: function (xhr, status, error) {
				$('#loading').hide();
				$('.search-wrapper').addClass('error');
				$('.noFound').html(`<span>❌ Error: ${error || 'Connection failed'}</span>`);
				$('.search_school').css('display', 'block');
				console.error("Search error:", error);
			}
		});
	});

	// Setup click handlers for schools (initial load)
	function setupSchoolClickHandlers(schools) {
		$(document).off('click', '.json__object');
		$(document).on('click', '.json__object', function (e) {
			e.stopPropagation();
			const index = $('.json__object').index(this);
			let nameValue = $(this).find('.name').text();

			$('#searchSchool').val(nameValue);

			let currentSchool = JSON.parse(schools[index].schoolInfo);
			let selectedSchool = new School(currentSchool);

			$('.json__object').removeClass('selected');
			$(this).addClass('selected');

			console.log("Selected school:", selectedSchool);

			$('#schoolData').val(JSON.stringify(selectedSchool));

			// Auto-submit after selection
			setTimeout(() => {
				$('#schoolForm').submit();
			}, 500);
		});
	}

	// Setup click handlers for initial page load
	if (arrayID > 0) {
		let initialSchools = [];
		$('.json__object').each(function () {
			initialSchools.push({
				schoolInfo: $(this).find('.schoolInfo').val()
					.replace(/(\w+)=/g, '"$1":')
					.replace(/'/g, '"')
			});
		});
		setupSchoolClickHandlers(initialSchools);
	}

	// Auto-suggest with debounce
	let searchTimeout;
	$('#searchSchool').on('input', function () {
		clearTimeout(searchTimeout);
		const value = $(this).val();

		if (value.length >= 3) {
			searchTimeout = setTimeout(() => {
				$('#searchForm').submit();
			}, 800);
		}
	});

	// Keyboard shortcuts
	$(document).on('keydown', function (e) {
		// Ctrl/Cmd + K to focus search
		if ((e.ctrlKey || e.metaKey) && e.key === 'k') {
			e.preventDefault();
			$('#searchSchool').focus();
		}

		// Escape to clear search
		if (e.key === 'Escape') {
			$('#searchSchool').val('');
			$('.search_school').hide();
			$('.search-wrapper').removeClass('error');
		}
	});
});


/*]]>*/