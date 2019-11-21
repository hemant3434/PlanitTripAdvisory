import React, { Component } from 'react';
import { Dimensions, StyleSheet , View} from 'react-native';
import MapView from 'react-native-maps';
import MapViewDirections from 'react-native-maps-directions';
import Geocoder from 'react-native-geocoding';

const { width, height } = Dimensions.get('window');
const ASPECT_RATIO = width / height;
const LATITUDE = 43.6532;
const LONGITUDE = -79.3832;
const LATITUDE_DELTA = 0.0922;
const LONGITUDE_DELTA = LATITUDE_DELTA * ASPECT_RATIO;

const OUR_KEY = 'AIzaSyDxwdE3kLIG6GehK-6h4DnLENeiayH2FYc';

class MapRoute extends Component {

  constructor(props) {
    super(props);
    this.state = {
      coordinates: []
    };
    this.populateState(this.props.common);
  }

  async populateState(Array){
    await Array.forEach(Event => {
      Geocoder.init(OUR_KEY);
      if(Event.type == "event"){
        Geocoder.from(Event.location)
        .then(json => {
            var location = json.results[0].geometry.location;
            this.state = {
              coordinates: [...this.state.coordinates, { latitude: location.lat,
              longitude: location.lng } ]
            }
        }).catch(error => console.warn(error));
      }
    })

  }

  onMapPress = (e) => {
    this.setState({
      coordinates: [
        ...this.state.coordinates,
        //e.nativeEvent.coordinate,
      ],
    });
  }

  render() {
    return (
      <MapView
        initialRegion={{
          latitude: LATITUDE,
          longitude: LONGITUDE,
          latitudeDelta: LATITUDE_DELTA,
          longitudeDelta: LONGITUDE_DELTA,
        }}
        style={StyleSheet.absoluteFill}
        ref={c => this.mapView = c}
        onPress={this.onMapPress}
      >
        {this.state.coordinates.map((coordinate, index) =>
          <MapView.Marker key={`coordinate_${index}`} coordinate={coordinate} />
        )}
        {(this.state.coordinates.length >= 2) && (
          <MapViewDirections
            origin={this.state.coordinates[0]}
            waypoints={ (this.state.coordinates.length > 2) ? this.state.coordinates.slice(1, -1): null}
            destination={this.state.coordinates[this.state.coordinates.length-1]}
            apikey={OUR_KEY}
            resetOnChange={false}
            strokeWidth={3}
            strokeColor="blue"
            optimizeWaypoints={true}
            onStart={(params) => {
              console.log(`Started routing between "${params.origin}" and "${params.destination}"`);
            }}
            onReady={result => {
              console.log('Distance: ${result.distance} km')
              console.log('Duration: ${result.duration} min.')

              this.mapView.fitToCoordinates(result.coordinates, {
                edgePadding: {
                  right: (width / 20),
                  bottom: (height / 20),
                  left: (width / 20),
                  top: (height / 20),
                }
              });
            }}
            onError={(errorMessage) => {
            }}
          />
        )}
      </MapView>
    );
  }
}

export default MapRoute;
