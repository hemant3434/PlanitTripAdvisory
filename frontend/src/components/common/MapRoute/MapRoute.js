import React, { Component } from 'react';
import { Dimensions, StyleSheet } from 'react-native';
import MapView from 'react-native-maps';
import MapViewDirections from 'react-native-maps-directions';

const { width, height } = Dimensions.get('window');
const ASPECT_RATIO = width / height;
<<<<<<< HEAD
const LATITUDE = 37.771707;
const LONGITUDE = -122.4053769;
=======
const LATITUDE = 43.6532;
const LONGITUDE = -79.3832;
>>>>>>> f0ca4c1b9275c4154d4ca9092a2e2eaff9da0704
const LATITUDE_DELTA = 0.0922;
const LONGITUDE_DELTA = LATITUDE_DELTA * ASPECT_RATIO;

const OUR_KEY = 'AIzaSyDxwdE3kLIG6GehK-6h4DnLENeiayH2FYc';

const GOOGLE_MAPS_APIKEY = '...';

class MapRoute extends Component {

  constructor(props) {
    super(props);
<<<<<<< HEAD

    // AirBnB's Office, and Apple Park
    this.state = {
      coordinates: [
        {
          place_id: this.props.startId,
        },
        {
          place_id: this.props.endId,
        },
      ],
    };

    this.mapView = null;
=======
    console.log("PROPS", this.props);
    // AirBnB's Office, and Apple Park
    this.state = {
      coordinates: []
    };
    this.populateState(this.props.common);
    console.log(this.state);
  }

  async populateState(Array){
    await Array.forEach(Event => {
      console.log("EVENTS!", Event);
      if(Event.type == "event"){
        this.state = {
          coordinates: [...this.state.coordinates, { latitude: Event.latitude,
          longitude: Event.longitude } ]
        }
      }
    })
    console.log(this.state);
>>>>>>> f0ca4c1b9275c4154d4ca9092a2e2eaff9da0704
  }

  onMapPress = (e) => {
    this.setState({
      coordinates: [
        ...this.state.coordinates,
        e.nativeEvent.coordinate,
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
<<<<<<< HEAD
            // apikey={OUR_KEY}
=======
            apikey={OUR_KEY}
>>>>>>> f0ca4c1b9275c4154d4ca9092a2e2eaff9da0704
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
              // console.log('GOT AN ERROR');
            }}
          />
        )}
      </MapView>
    );
  }
}

export default MapRoute;
