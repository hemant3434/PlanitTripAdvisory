import React from 'react';
import { StyleSheet, View, Text, Dimensions, ScrollView } from 'react-native';
import MapView, { Marker, Circle, ProviderPropType } from 'react-native-maps';

const { width, height } = Dimensions.get('window');

const ASPECT_RATIO = width / height;

class MapPicker extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            region: {
                latitude: 43.784305,
                longitude: -79.187589,
                latitudeDelta: 0.0922,
                longitudeDelta: 0.0922 * ASPECT_RATIO,
            },
            radius: 500,
        };
    }

    render () {
        return (
            <View style={styles.container}>
                <ScrollView
                style={StyleSheet.absoluteFill}
                contentContainerStyle={styles.scrollview}
                >
                    <MapView
                    provider={this.props.provider}
                    style={styles.map}
                    scrollEnabled={false}
                    zoomEnabled={false}
                    pitchEnabled={false}
                    rotateEnabled={false}
                    initialRegion={this.state.region}
                    >
                        <Marker
                        title="This is a title"
                        description="This is a description"
                        coordinate={this.state.region}
                        />
                        <MapView.Circle
                        center = {}
                        />
                    </MapView>
                </ScrollView>
            </View>
    
        );
    }
}

MapPicker.propTypes = {
    provider: ProviderPropType,
};

const styles = StyleSheet.create({
    container: {
      ...StyleSheet.absoluteFillObject,
      justifyContent: 'flex-end',
      alignItems: 'center',
    },
    scrollview: {
      alignItems: 'center',
      paddingVertical: 40,
    },
    map: {
      width: 250,
      height: 250,
    },
  });  

export default MapPicker;