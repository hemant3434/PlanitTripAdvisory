import React from 'react';
import LocationView from "react-native-location-view";
import {View} from "react-native";

const api = "AIzaSyDxwdE3kLIG6GehK-6h4DnLENeiayH2FYc";

export default class MapPicker extends React.Component {

  constructor(props){
    super(props);
  }

  render() {
    return(
      <View style={{flex: 1}}>
        <LocationView
          apiKey={api}
          initialLocation={{
            latitude: 43.6532,
            longitude: -79.3832,
          }}
          onLocationSelect={this.props.onLocationSelectProp}
        />
      </View>
    );
  }
}
