import React from 'react';
import LocationView from "react-native-location-view";
import {View} from "react-native";

const api = "AIzaSyBNLrbh_D9AftY73lfJbKeRKcTH-av7MDk";

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
        />
      </View>
    );
  }
}
